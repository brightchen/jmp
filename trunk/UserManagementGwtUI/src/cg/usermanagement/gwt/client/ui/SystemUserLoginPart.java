package cg.usermanagement.gwt.client.ui;

import cg.gwt.components.client.ui.Part;
import cg.usermanagement.gwt.client.LoginHandler;
import cg.usermanagement.gwt.shared.data.SystemUserLoginData;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class SystemUserLoginPart extends Part< SystemUserLoginData, FlexTable >
{
  private TextBox nameField;
  private TextBox passwordField;
  
  //create an empty data instance
  @Override
  protected SystemUserLoginData createData()
  {
    return new SystemUserLoginData();
  }

  @Override
  public FlexTable build()
  {
    SystemUserLoginData data = getData();
    
    FlexTable table = new FlexTable();

    table.setText( 0, 0, "system user name" );
    nameField = new TextBox();
    nameField.setText( data == null ? "" : data.getUserName() );
    nameField.setFocus( true );
    nameField.selectAll();
    table.setWidget( 1, 0, nameField );

    table.setText( 0, 1, "password" );
    passwordField = new PasswordTextBox();
    passwordField.setText( "" );
    table.setWidget( 1, 1, passwordField );

    // login button
    {
      final Button loginButton = new Button( "Login" );
      // Add a handler to send the name to the server
      LoginHandler handler =  new LoginHandler()
                              {
                                @Override
                                protected void updateData()
                                {
                                  SystemUserLoginPart.this.updateData();
                                  SystemUserLoginData data = getData();
                                  setUserName( data.getUserName() );
                                  setPassword( data.getPassword() );
                                }
                              };
      
      loginButton.addClickHandler( handler );
      loginButton.addKeyUpHandler( handler );

      table.setWidget( 1, 2, loginButton );
    }
    return table;
    
  }
  
  //get the data from UI
  public void updateData()
  {
    SystemUserLoginData data = getData();
    if( data == null )
    {
      data = new SystemUserLoginData();
      setData( data );
    }
    data.setUserName( nameField.getText() );
    data.setPassword( passwordField.getText() );
  }

}