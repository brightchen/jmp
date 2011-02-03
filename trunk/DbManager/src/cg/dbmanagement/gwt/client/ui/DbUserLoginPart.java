package cg.dbmanagement.gwt.client.ui;

import cg.dbmanagement.gwt.shared.data.DbUserLoginData;
import cg.gwt.components.client.ui.Part;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class DbUserLoginPart extends Part< DbUserLoginData, FlexTable >
{
  private ListBox databaseList = new ListBox();
  private TextBox userNameField;
  private TextBox passwordField;
  
  @Override
  public FlexTable build()
  {
    DbUserLoginData data = getData();
    FlexTable table = new FlexTable();

    int row = 0;
    table.setText( row, 0, "select database type: " );
    table.setWidget( row, 1, databaseList );

    ++row;
    table.setText( row, 0, "user name: " );
    userNameField = new TextBox();
    userNameField.setText( data == null ? "" : data.getUserName() );
    table.setWidget( row, 1, databaseList );

    ++row;
    table.setText( row, 0, "password: " );
    passwordField = new TextBox();
    passwordField.setText( "" );
    table.setWidget( row, 1, passwordField );

    // login button
    {
      ++row;
      final Button loginButton = new Button( "Login" );
      table.setWidget( row, 0, loginButton );
    }
    
//    refreshDatabases();
    
    return table;
  }

  @Override
  public void updateData()
  {
    // TODO Auto-generated method stub
    
  }

}
