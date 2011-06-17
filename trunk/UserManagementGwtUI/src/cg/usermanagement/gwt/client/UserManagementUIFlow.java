package cg.usermanagement.gwt.client;

import com.google.gwt.user.client.ui.RootPanel;

import cg.gwt.components.client.ui.UIComponent;
import cg.gwt.components.client.ui.components.SimpleMessageDialogUI;
import cg.gwt.components.shared.data.ButtonData;
import cg.usermanagement.gwt.client.role.RoleDetailUI;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.LoginData;
import cg.usermanagement.gwt.shared.data.RoleData;
import cg.usermanagement.gwt.shared.data.UserLoginData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelData;
import cg.usermanagement.gwt.shared.data.UserRegisterData;

public class UserManagementUIFlow
{
  /*
   * this is the UI to allow user/account login and register
   * no permission required for this UI
   */
  public static UIComponent< ?, ? > buildStartUI()
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
        RootPanel.get().add( buildUserManagementPanelUI() );
      }
    } ).centre();
    
  }


  /*
   * this is the UI allow user the manage users,
   * such as user account management; account role management; role permission management etc
   */
  public static UIComponent< ?, ? > buildUserManagementPanelUI()
  {
    UserManagementPanelData data = new UserManagementPanelData();
    for( UserManagementButtonMeta meta : UserManagementButtonMeta.values() )
    {
      data.addButtonData( meta.getButtonData() );
    }
    return new UserManagementPanelUI( data );
  }
  
  public static void doAddRole()
  {
    RootPanel.get().clear();
    RootPanel.get().add( buildAddRoleUI() );
  }
  
  public static UIComponent< ?, ? > buildAddRoleUI()
  {
    RoleData roleData = new RoleData();
    ButtonData buttonData = roleData.getSaveButtonData();
    buttonData.setText( "Add Role" );
    buttonData.setTitle( "Add a new Role" );
    return new RoleDetailUI( roleData );
  }
}
