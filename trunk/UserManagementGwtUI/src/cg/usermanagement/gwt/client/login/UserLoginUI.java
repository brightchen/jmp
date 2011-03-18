package cg.usermanagement.gwt.client.login;

import cg.usermanagement.gwt.shared.data.SystemUserLoginData;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class UserLoginUI extends FlexTable
{
  private TextBox accountField;
  private TextBox passwordField;
  private SystemUserLoginData data;
  
  public UserLoginUI( SystemUserLoginData data )
  {
    setData( data );
  }
  
  public void setData( SystemUserLoginData data )
  {
    this.data = data;
  }

  public SystemUserLoginData getData()
  {
    return data;
  }
  
  public void build()
  {
    setText( 0, 0, "system account name/id:" );
    accountField = new TextBox();
    accountField.setText( data == null ? "" : data.getAccountId() );
    accountField.setFocus( true );
    accountField.selectAll();
    setWidget( 1, 0, accountField );

    setText( 0, 1, "password" );
    passwordField = new PasswordTextBox();
    passwordField.setText( "" );
    setWidget( 1, 1, passwordField );

    // login button
    {
      final Button loginButton = new Button( "Login" );
      setWidget( 1, 2, loginButton );
    }
  }

  @Override
  public void onLoad()
  {
    build();
  }
}
