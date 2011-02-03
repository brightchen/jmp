package cg.usermanagement.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LoginHandler implements ClickHandler, KeyUpHandler
{
  private IAuthenticateServiceAsync userService = GWT.create( IAuthenticateService.class );
  
  private String userName;
  private String password;
  
  public LoginHandler()
  {
  }
  /**
   * Fired when the user clicks on the sendButton.
   */
  public void onClick( ClickEvent event )
  {
    onLogin();
  }

  public void onKeyUp( KeyUpEvent event )
  {
    if ( event.getNativeKeyCode() == KeyCodes.KEY_ENTER )
    {
      onLogin();
    }
  }

  //get data from UI
  protected void updateData(){}
  
  protected void onLogin()
  {
    updateData();
    
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
  public String getUserName()
  {
    return userName;
  }
  public void setUserName( String userName )
  {
    this.userName = userName;
  }
  public String getPassword()
  {
    return password;
  }
  public void setPassword( String password )
  {
    this.password = password;
  }
  
  
}