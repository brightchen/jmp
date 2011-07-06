package cg.usermanagement.gwt.shared.data;

import cg.gwt.components.shared.data.MenuBarData;
import cg.gwt.components.shared.data.MenuItemData;
import cg.gwt.components.shared.data.MenuItemType;
import cg.gwt.components.shared.data.NormalMenuItemData;


public class UserManagementMenuBarData extends MenuBarData
{
  private static final long serialVersionUID = -1285359300101461795L;

  //TODO: provide the command keys management later
  public static final String CK_ADD_USER = "UserManagement/AddUser";
  public static final String CK_EDIT_USER = "UserManagement/EditUser";
  public static final String CK_REMOVE_USER = "UserManagement/RemoveUser";
  
  public static UserManagementMenuBarData getTypicalData()
  {
    UserManagementMenuBarData userManagementData = new UserManagementMenuBarData();
    userManagementData.setTitle( "User Management" );
    userManagementData.addMenuItemData( new NormalMenuItemData( "Add User", CK_ADD_USER ) );
    userManagementData.addMenuItemData( new NormalMenuItemData( "Edit User", CK_EDIT_USER ) );
    userManagementData.addMenuItemData( new NormalMenuItemData( "Remove User", CK_REMOVE_USER ) );
    //add a separator
    userManagementData.addMenuItemData( new MenuItemData( MenuItemType.SEPARATOR ) );
    
    //account
    MenuBarData accountMenuBar = new MenuBarData();
    accountMenuBar.setTitle( "Account" );
    accountMenuBar.addMenuItemData( new NormalMenuItemData( "Add Account", CK_ADD_USER ) );
    accountMenuBar.addMenuItemData( new NormalMenuItemData( "Edit Account", CK_EDIT_USER ) );
    accountMenuBar.addMenuItemData( new NormalMenuItemData( "Remove Account", CK_REMOVE_USER ) );
    userManagementData.addMenuItemData( accountMenuBar );
    
    return userManagementData;
  }
  
  public UserManagementMenuBarData()
  {
  }
  
}
