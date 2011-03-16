package cg.usermanagement.gwt.client.login;

import cg.gwt.components.client.ui.IBuildable;
import cg.usermanagement.gwt.shared.data.SystemUserLoginData;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class UserLoginUI extends FlexTable implements IBuildable< SystemUserLoginData >
{
  private TextBox nameField;
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

  @Override
  public SystemUserLoginData getData()
  {
    return data;
  }
  
  @Override
  public void build()
  {
    setText( 0, 0, "system account name/id:" );
    nameField = new TextBox();
    nameField.setText( data == null ? "" : data.getAccountId() );
    nameField.setFocus( true );
    nameField.selectAll();
    setWidget( 1, 0, nameField );

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
