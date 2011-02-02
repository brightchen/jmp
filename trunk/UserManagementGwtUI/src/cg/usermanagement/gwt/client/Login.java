package cg.usermanagement.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Login implements EntryPoint
{
  public void onModuleLoad()
  {
    FlexTable table = new FlexTable();

    table.setText(0, 0, "user name");
    final TextBox nameField = new TextBox();
    nameField.setText( "input user name here" );
    nameField.setFocus( true );
    nameField.selectAll();
    table.setWidget( 1, 0, nameField );
    
    table.setText(0, 1, "password");
    final TextBox passwordField = new TextBox();
    passwordField.setText( "input password here" );
    table.setWidget( 1, 1, passwordField );

    //login button
    {
      final Button loginButton = new Button("Login");
      // Add a handler to send the name to the server
      LoginHandler handler = new LoginHandler( nameField, passwordField );
      loginButton.addClickHandler( handler );
      loginButton.addKeyUpHandler( handler );
  
      table.setWidget(1, 2, loginButton);
    }
    
    RootPanel rp = RootPanel.get();
    rp.add( table );
  }    

}
