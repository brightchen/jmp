package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.MessageDialog;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;

public class LoginHandler implements ClickHandler, KeyUpHandler
{
  private IAuthenticateServiceAsync userService = GWT.create( IAuthenticateService.class );
  
  private String accountId;
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
    
    userService.login( accountId, password, 
                       new PopupFailureReasonCallback< Void >()
                        {
                          @Override
                          public void onSuccess( Void returned )
                          {
                            (new MessageDialog()).displayMessage( "login successful" );
                          }
                        } );
  }
  public String getAccountId()
  {
    return accountId;
  }
  public void setAccountId( String accountId )
  {
    this.accountId = accountId;
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