package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.UIComponent;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.UserLoginData;
import cg.usermanagement.gwt.shared.data.UserRegisterData;

public class UserManagementWorkflow
{
  public static UIComponent< ?, ? > getStartUI()
  {
    UserManagementUI userManagementUI = new UserManagementUI( new UserLoginData(), new AccountLoginData(), new UserRegisterData() );
    return userManagementUI;
  }

}
