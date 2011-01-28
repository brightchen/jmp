package cg.usermanagement.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;

public class LoginHandler implements ClickHandler, KeyUpHandler
{
  private TextBox tbUserName;
  private TextBox tbPassword;
  private IAuthenticateServiceAsync userService = GWT.create( IAuthenticateService.class );
  
  public LoginHandler( TextBox tbUserName, TextBox tbPassword )
  {
    this.tbUserName = tbUserName;
    this.tbPassword = tbPassword;
  }
  /**
   * Fired when the user clicks on the sendButton.
   */
  public void onClick( ClickEvent event )
  {
    onLogin();
  }

  /**
   * Fired when the user types in the nameField.
   */
  public void onKeyUp( KeyUpEvent event )
  {
    if ( event.getNativeKeyCode() == KeyCodes.KEY_ENTER )
    {
      onLogin();
    }
  }

  /**
   * Send the name from the nameField to the server and wait for a response.
   */
  private void onLogin()
  {
    String userName = tbUserName.getText();
    String password = tbPassword.getText();
    userService.login( userName, password, new AsyncCallback< Void >()
    {
      @Override
      public void onFailure( Throwable exception )
      {
        (new MessageDialog()).displayMessage( "login failed due to " + exception.toString() );
      }
      
      @Override
      public void onSuccess( Void returned )
      {
        (new MessageDialog()).displayMessage( "login successful" );
      }
    } );
  }
}