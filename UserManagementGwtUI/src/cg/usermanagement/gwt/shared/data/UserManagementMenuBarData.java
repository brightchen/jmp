package cg.usermanagement.gwt.shared.data;


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
    return userManagementData;
  }
  
  public UserManagementMenuBarData()
  {
  }
  
}
