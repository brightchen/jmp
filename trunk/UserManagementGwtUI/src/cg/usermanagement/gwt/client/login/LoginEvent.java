package cg.usermanagement.gwt.client.login;

import java.util.List;

import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.gwt.components.shared.data.ResponseData;
import cg.gwt.components.shared.data.ValidateException;
import cg.usermanagement.gwt.client.UserManagementEvent;
import cg.usermanagement.gwt.client.UserManagementUIFlow;
import cg.usermanagement.gwt.shared.data.LoginData;
import cg.usermanagement.gwt.shared.data.LoginType;

@SuppressWarnings( "rawtypes" ) 
public abstract class LoginEvent extends UserManagementEvent< LoginData >
{
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
      getUserManagement().userlogin( data.getName(), data.getPassword(), 
                                new PopupFailureReasonCallback< List< ResponseData<?> > >()
                                 {
                                   @Override
                                   public void onSuccess( List< ResponseData<?> > responseDatas )
                                   {
                                     onLoginSuccess( data, responseDatas );
                                   }
                                 } );
    }
    else if( LoginType.ACCOUNT_LOGIN.equals( loginType ) ) 
    {
      getUserManagement().accountlogin( data.getName(), data.getPassword(), 
                                   new PopupFailureReasonCallback< List< ResponseData<?> > >()
                                    {
                                      @Override
                                      public void onSuccess( List< ResponseData<?> > responseDatas )
                                      {
                                        onLoginSuccess( data, responseDatas );
                                      }
                                    } );
    }
    else
    {
      // exception
    }
  }

  protected void onLoginSuccess( LoginData loginData, List< ResponseData<?> > responseDatas )
  {
    UserManagementUIFlow.onLoginSuccess( loginData, responseDatas );
  }

}