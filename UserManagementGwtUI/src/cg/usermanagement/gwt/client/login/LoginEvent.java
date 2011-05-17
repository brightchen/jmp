package cg.usermanagement.gwt.client.login;

import java.io.Serializable;

import cg.gwt.components.client.ui.components.SimpleErrorDialogUI;
import cg.gwt.components.client.ui.components.SimpleMessageDialogUI;
import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.usermanagement.gwt.client.IAuthenticateService;
import cg.usermanagement.gwt.client.IAuthenticateServiceAsync;
import cg.usermanagement.gwt.shared.LoginException;

import com.google.gwt.core.client.GWT;

public abstract class LoginEvent extends UIEvent< LoginEvent.LoginEventData >
{
  private IAuthenticateServiceAsync userService = GWT.create( IAuthenticateService.class );
  
  public LoginEvent()
  {
  }
  
  @Override
  public void fire()
  {
    onLogin();
  }
  
  protected void onLogin()
  {
    userService.login( getData().getAccountId(), getData().getPassword(), 
                       new PopupFailureReasonCallback< Void >()
                        {
                          @Override
                          public void onFailure( Throwable caught )
                          {
                            if( !( caught instanceof LoginException ) )
                            {
                              ( new SimpleErrorDialogUI( caught.getMessage() ) ).centre();
                            }
                            else
                            {
                              LoginException le = (LoginException)caught;
                              ( new SimpleErrorDialogUI(  le.getErrorReason() ) ).centre();
                            }
                          }

                          @Override
                          public void onSuccess( Void returned )
                          {
                            onLoginSuccess();
                          }
                        } );
  }

  protected void onLoginSuccess()
  {
    (new SimpleMessageDialogUI( "login successful."  )).show( );
  }
  
  public static class LoginEventData implements Serializable
  {
    private static final long serialVersionUID = -3973984244159981528L;

    private String accountId;
    private String password;
    
    public LoginEventData( String accountId, String password )
    {
      this.accountId = accountId;
      this.password = password;
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
}