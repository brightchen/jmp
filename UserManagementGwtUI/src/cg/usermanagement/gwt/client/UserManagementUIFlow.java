package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.UIComponent;
import cg.gwt.components.client.ui.decorator.SimplePopupDecorator;
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
  //the system is using ajax, one static addRolePopup is enough. 
  //when creating, addRolePopup is same for all users. when operation on this popup, it's the client side use the javascript to operate the popup
  private static SimplePopupDecorator<?> addRolePopup;
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
  
  /*
   * the system behavior when user clicking the add role button
   */
  public static void doAddRole()
  {
    if( addRolePopup == null )
    {
      AddRoleData roleData = new AddRoleData();
      ButtonData buttonData = roleData.getSaveButtonData();
      buttonData.setText( "Add Role" );
      buttonData.setTitle( "Add a new Role" );
      addRolePopup = ( new SimplePopupDecorator< AddRoleUI >( "Add Role", new AddRoleUI( roleData ) ) );
    }
    addRolePopup.centre();
  }
  
  /*
   * switch to role detail ui to assign permissions
   */
  public static void onAddRoleSuccess( AddRoleData addRoleData )
  {
    addRolePopup.hide( true );
  }
  
  public static UIComponent< ?, ? > buildRoleDetailUI()
  {
    RoleDetailData roleDetailData = new RoleDetailData();
    RoleDetailUI roleDetailUI = new RoleDetailUI( roleDetailData );
    return roleDetailUI;
  }
}
