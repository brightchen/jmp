package cg.usermanagement.gwt.client.login;

import cg.gwt.components.client.ui.components.SimpleErrorDialogUI;
import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.gwt.components.shared.data.ValidateException;
import cg.usermanagement.gwt.client.IAuthenticateService;
import cg.usermanagement.gwt.client.IAuthenticateServiceAsync;
import cg.usermanagement.gwt.shared.data.LoginData;
import cg.usermanagement.gwt.shared.data.LoginType;
import cg.usermanagement.shared.LoginException;

import com.google.gwt.core.client.GWT;

public abstract class LoginEvent extends UIEvent< LoginData >
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
    final LoginData data = getData();
    try
    {
      data.validate();
    }
    catch( ValidateException e )
    {
      //TODO: handle the exception7
    }

    LoginType loginType = data.getLoginType();
    if( LoginType.USER_LOGIN.equals( loginType ) )
    {
      userService.userlogin( data.getName(), data.getPassword(), 
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
                                     onLoginSuccess( data );
                                   }
                                 } );
    }
    else if( LoginType.ACCOUNT_LOGIN.equals( loginType ) ) 
    {
      userService.accountlogin( data.getName(), data.getPassword(), 
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
                              onLoginSuccess( data );
                            }
                          } );
    }
    else
    {
      // exception
    }
  }

  protected void onLoginSuccess( LoginData data )
  {
    LoginWorkflow.onLoginSuccess( data );
  }

}