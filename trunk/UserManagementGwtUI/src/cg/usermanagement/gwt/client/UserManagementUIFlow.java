package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.UIComponent;
import cg.gwt.components.shared.data.ButtonData;
import cg.usermanagement.gwt.client.role.AddRoleUI;
import cg.usermanagement.gwt.client.role.RoleDetailUI;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.AddRoleData;
import cg.usermanagement.gwt.shared.data.LoginData;
import cg.usermanagement.gwt.shared.data.RoleDetailData;
import cg.usermanagement.gwt.shared.data.UserLoginData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelData;
import cg.usermanagement.gwt.shared.data.UserRegisterData;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

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
    RootPanel.get().clear();
    RootPanel.get().add( buildUserManagementPanelUI() );
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
  
  public static Widget buildAddRoleUI()
  {
    AddRoleData roleData = new AddRoleData();
    ButtonData buttonData = roleData.getSaveButtonData();
    buttonData.setText( "Add Role" );
    buttonData.setTitle( "Add a new Role" );
    return new AddRoleUI( roleData );
  }
  
  /*
   * switch to role detail ui to assign permissions
   */
  public static void onAddRoleSuccess( AddRoleData addRoleData )
  {
    RootPanel.get().clear();
    RootPanel.get().add( buildRoleDetailUI() );
    
  }
  
  public static UIComponent< ?, ? > buildRoleDetailUI()
  {
    RoleDetailData roleDetailData = new RoleDetailData();
    RoleDetailUI roleDetailUI = new RoleDetailUI( roleDetailData );
    return roleDetailUI;
  }
}
