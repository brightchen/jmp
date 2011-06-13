package cg.usermanagement.gwt.client.login;

import cg.gwt.components.client.ui.UIComponent;
import cg.gwt.components.client.ui.components.SimpleMessageDialogUI;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.LoginData;
import cg.usermanagement.gwt.shared.data.UserLoginData;

/*
 * for the login workflow, the User hasn't set. so, all the method should be static
 */
public class LoginWorkflow
{
  
  public static UIComponent< ?, ? > getStartUI()
  {
    LoginStartUI loginStartUI = new LoginStartUI( new UserLoginData(), new AccountLoginData() );
    return loginStartUI;
  }

  public static void onLoginSuccess( LoginData data )
  {
    (new SimpleMessageDialogUI( "login successful."  )).centre();
  }

}
