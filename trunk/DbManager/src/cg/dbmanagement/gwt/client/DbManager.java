package cg.dbmanagement.gwt.client;

import cg.usermanagement.gwt.client.ui.SystemUserLoginPart;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DbManager implements EntryPoint
{
  private IConfigServiceAsync configService = GWT.create( IConfigService.class );
  private ListBox databaseList = new ListBox();
  
  /**
   * This is the entry point method.
   */
  public void onModuleLoad()
  {

    //TabLayoutPanel seems don't work and TabPanel doesn't deprecated in gwt2.1.1
    // TabLayoutPanel tp = new TabLayoutPanel( 1.5, Unit.EM );
    //use the lazy initialize
    TabPanel tp = new TabPanel();
    tp.add( buildSystemLogin(), "System" );
    tp.add( buildDatabaseLogin(), "Database" );
    tp.addSelectionHandler( new SelectionHandler< Integer >()
                            {
                              @Override
                              public void onSelection( SelectionEvent<Integer> event )
                              {
                                Integer selectItemIndex = event.getSelectedItem();
                                if( selectItemIndex == 1 )
                                {
                                  //the database login tab is selected
                                  refreshDatabases();
                                }
                              }
                            } );

    tp.selectTab( 0 );

    //this module using UserManagementGwtUI, remove the widget added by UserManagementGwtUI
    RootPanel.get().remove( 0 );

    // Add it to the root panel.
    RootPanel.get().add( tp );

  }

  protected Widget buildSystemLogin()
  {
    SystemUserLoginPart loginPart = new SystemUserLoginPart();
    
    return loginPart.build();
  }

  protected Widget buildDatabaseLogin()
  {
    FlexTable table = new FlexTable();

    table.setText( 0, 0, "select database type: " );
    table.setWidget( 0, 1, databaseList );
    
    table.setText( 1, 0, "password" );
    final TextBox passwordField = new TextBox();
    passwordField.setText( "" );
    table.setWidget( 1, 1, passwordField );

    // login button
    {
      final Button loginButton = new Button( "Login" );
      // Add a handler to send the name to the server
//      LoginHandler handler = new LoginHandler( nameField, passwordField );
//      loginButton.addClickHandler( handler );
//      loginButton.addKeyUpHandler( handler );

      table.setWidget( 2, 0, loginButton );
    }
    
//    refreshDatabases();
    
    return table;
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
