package cg.usermanagement.gwt.client;

import com.google.gwt.user.client.ui.RootPanel;

import cg.gwt.components.client.ui.UIComponent;
import cg.gwt.components.client.ui.components.SimpleMessageDialogUI;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.LoginData;
import cg.usermanagement.gwt.shared.data.UserLoginData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelData;
import cg.usermanagement.gwt.shared.data.UserRegisterData;

public class UserManagementUIFlow
{
  /*
   * this is the UI to allow user/account login and register
   * no permission required for this UI
   */
  public static UIComponent< ?, ? > getStartUI()
  {
    UserManagementUI userManagementUI = new UserManagementUI( new UserLoginData(), new AccountLoginData(), new UserRegisterData() );
    return userManagementUI;
  }
  
  public static void onLoginSuccess( LoginData data )
  {
    (new SimpleMessageDialogUI( "login successful."  )
    {
      @Override
      protected void onOkButtonClick()
      {
        super.onOkButtonClick();
        RootPanel.get().clear();
        RootPanel.get().add( getUserManagementPanelUI() );
      }
    } ).centre();
    
  }


  /*
   * this is the UI allow user the manage users,
   * such as user account management; account role management; role permission management etc
   */
  public static UIComponent< ?, ? > getUserManagementPanelUI()
  {
    UserManagementPanelData data = new UserManagementPanelData();
    for( UserManagementButtonMeta meta : UserManagementButtonMeta.values() )
    {
      data.addButtonData( meta.getButtonData() );
    }
    return new UserManagementPanelUI( data );
  }
}
