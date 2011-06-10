package cg.usermanagement.gwt.client.login;

import cg.gwt.components.client.ui.UIComponent;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.UserLoginData;

public class LoginWorkflow
{
  
  public UIComponent< ?, ? > getStartUI()
  {
    LoginStartUI loginStartUI = new LoginStartUI( new UserLoginData(), new AccountLoginData() );
    return loginStartUI;
  }
}
