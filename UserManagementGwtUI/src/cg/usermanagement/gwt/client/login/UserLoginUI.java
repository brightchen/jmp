package cg.usermanagement.gwt.client.login;

import cg.gwt.components.client.ui.dp.DataPresentation;
import cg.usermanagement.gwt.shared.data.SystemUserLoginData;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class UserLoginUI extends DataPresentation< SystemUserLoginData, FlexTable >
{
  private TextBox accountField;
  private TextBox passwordField;
  
  public UserLoginUI( SystemUserLoginData data )
  {
    setData( data );
  }
  
  public FlexTable build()
  {
    FlexTable t = new FlexTable();
    t.setText( 0, 0, "system account name/id:" );
    accountField = new TextBox();
    accountField.setText( getData() == null ? "" : getData().getAccountId() );
    accountField.setFocus( true );
    accountField.selectAll();
    t.setWidget( 1, 0, accountField );

    t.setText( 0, 1, "password" );
    passwordField = new PasswordTextBox();
    passwordField.setText( "" );
    t.setWidget( 1, 1, passwordField );

    // login button
    {
      final Button loginButton = new Button( "Login" );
      t.setWidget( 1, 2, loginButton );
    }
    return t;
  }
  
}
