package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.UIComponent;
import cg.gwt.components.client.ui.decorator.PopupDecorator;
import cg.gwt.components.client.ui.decorator.PopupWithCancelButtonDecorator;
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
  //the static attribute is safe as this is client code and run in the web browser.
  //the static is only static for one client web browser.
  private static PopupDecorator<?,?> addRolePopup;
  private static PopupDecorator<?,?> roleDetailPopup;
  
  /*
   * this is the UI to allow user/account login and register
   * no permission required for this UI
   */
  public static void start()
  {
    RootPanel rp = RootPanel.get();
    rp.add( buildStartUI() );

  }
  public static Widget buildStartUI()
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
  
  public static void doSearchUser()
  {
    
  }
  
  public static void doSearchAccount()
  {
    
  }

  public static void doAddAccount()
  {
    
  }

  public static void doSearchRole()
  {
    
  }

  /*
   * the system behavior when user clicking the add role button
   */
  public static void doAddRole()
  {
    //the addRolePopup should be recreate even if addRolePopup is not null, 
    //as addRolePopup can be already closed( for example, one user create two roles ),
    //which made any operation to addRolePopup is invalid
    AddRoleData roleData = new AddRoleData();
    ButtonData buttonData = roleData.getSaveButtonData();
    buttonData.setText( "Add Role" );
    buttonData.setTitle( "Add a new Role" );
    addRolePopup = new PopupWithCancelButtonDecorator< AddRoleUI >( "Add Role", new AddRoleUI( roleData ) );
    addRolePopup.centre();
  }
  
  /*
   * switch to role detail ui to assign permissions
   */
  public static void onAddRoleSuccess( AddRoleData addRoleData )
  {
    addRolePopup.hide( true );
    
    roleDetailPopup = new PopupWithCancelButtonDecorator< Widget >( "Role Detail", buildRoleDetailUI( addRoleData.getId(), addRoleData.getName() ) );
    roleDetailPopup.centre();
  }
  
  public static Widget buildRoleDetailUI( Long roleId, String roleName )
  {
    RoleDetailData roleDetailData = new RoleDetailData();
    roleDetailData.setId( roleId );
    roleDetailData.setName( roleName );
    RoleDetailUI roleDetailUI = new RoleDetailUI( roleDetailData );
    return roleDetailUI;
  }
}
