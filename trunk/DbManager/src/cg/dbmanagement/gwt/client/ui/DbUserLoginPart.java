package cg.dbmanagement.gwt.client.ui;

import cg.dbmanagement.gwt.client.IConfigService;
import cg.dbmanagement.gwt.client.IConfigServiceAsync;
import cg.dbmanagement.gwt.shared.data.DbUserLoginData;
import cg.gwt.components.client.ui.LazyLoadingPart;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class DbUserLoginPart extends LazyLoadingPart< DbUserLoginData, FlexTable >
{
  private ListBox databaseList = new ListBox();
  private TextBox urlField;
  private TextBox userNameField;
  private TextBox passwordField;
  
  
  private IConfigServiceAsync configService = GWT.create( IConfigService.class );
  
  @Override
  public FlexTable build()
  {
    DbUserLoginData data = getData();
    FlexTable table = new FlexTable();

    // database type
    int row = 0;
    table.setText( row, 0, "select database type: " );
    databaseList.addChangeHandler( new ChangeHandler()
                                    {
                                      @Override
                                      public void onChange( ChangeEvent event )
                                      {
                                        doSelectedDatabaseChanged();
                                      }
                                      
                                    } );
    table.setWidget( row, 1, databaseList );

    //jdbc url
    table.setText( row, 0, "jdbc url: " );
    urlField = new TextBox();
    urlField.setText( data == null ? "" : data.getJdbcUrl() );
    table.setWidget( row, 1, urlField );
    
    //user name
    ++row;
    table.setText( row, 0, "user name: " );
    userNameField = new TextBox();
    userNameField.setText( data == null ? "" : data.getUserName() );
    table.setWidget( row, 1, userNameField );

    //password
    ++row;
    table.setText( row, 0, "password: " );
    passwordField = new PasswordTextBox();
    passwordField.setText( "" );
    table.setWidget( row, 1, passwordField );

    // login button
    {
      ++row;
      final Button connectButton = new Button( "Connect to DB" );
      connectButton.addClickHandler( new ClickHandler()
                                    {
                                      @Override
                                      public void onClick( ClickEvent event )
                                      {
                                        doConnectDbButtonClicked();
                                      }
                                
                                    } );
      table.setWidget( row, 0, connectButton );
    }
    
    return table;
  }

  protected void doSelectedDatabaseChanged()
  {
    getData().setSelectedDatabaseIndex( databaseList.getSelectedIndex() );
  }
  
  protected void doConnectDbButtonClicked()
  {
    updateData();
    connectToDB();
  }

  protected void connectToDB()
  {
    
  }
  
  @Override
  public void load()
  {
    //this method need to call the service, put it into the lazy loading
    refreshDatabases();
  }
  
  @Override
  public void updateData()
  {
    DbUserLoginData data = getData();
    if( data == null )
    {
      data = new DbUserLoginData();
      setData( data );
    }
    data.setSelectedDatabaseIndex( databaseList.getSelectedIndex() );
    data.setJdbcUrl( urlField.getText() );
    data.setUserName( userNameField.getText() );
    data.setPassword( passwordField.getText() );
  }

  protected void refreshDatabases()
  {
    configService.getSupportedDatabases( new AsyncCallback< String[] >()
    {
      @Override
      public void onFailure( Throwable caught )
      {
      }

      @Override
      public void onSuccess( String[] result )
      {
        refreshDatabases( result );
      }
    } );
  }

  protected void refreshDatabases( String[] databases )
  {
    databaseList.clear();
    getData().setSupportedDatabases( databases );
    if( databases != null && databases.length > 0 )
    {
      for( String db : databases )
      {
        if( db == null || db.isEmpty() )
          continue;
        databaseList.addItem( db );
      }
    }
    
  }

}
