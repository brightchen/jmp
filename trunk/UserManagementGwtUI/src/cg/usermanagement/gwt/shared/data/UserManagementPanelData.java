package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.shared.data.ButtonData;
import cg.gwt.components.shared.data.UIContentData;
import cg.resourcemanagement.annotation.IResourceKey;

public class UserManagementPanelData extends UIContentData<UserManagementPanelResourceData> implements Serializable
{
  private static final long serialVersionUID = -2787639626749698020L;

  private List< ButtonData > buttonDatas = new ArrayList< ButtonData >();

//  um.usermanagementpanel_searchuser.text=Search User
//  um.usermanagementpanel_searchaccount.text=Search Account
//  um.usermanagementpanel_addaccount.text=Add Account
//  um.usermanagementpanel_searchrole.text=Search Role
//  um.usermanagementpanel_addrole.text=Add Role
//  um.usermanagementpanel_addpermission.text=Add Permission

  private ButtonData searchUserButtonData = new ButtonData();
  private ButtonData searchAccountButtonData = new ButtonData();
  private ButtonData addAccountButtonData = new ButtonData();
  private ButtonData searchRoleButtonData = new ButtonData();
  private ButtonData addRoleButtonData = new ButtonData();
  private ButtonData addPermissionButtonData = new ButtonData();
  
  public UserManagementPanelData()
  {
    addButtonData( searchUserButtonData );
    addButtonData( searchAccountButtonData );
    addButtonData( addAccountButtonData );
    addButtonData( searchRoleButtonData );
    addButtonData( addRoleButtonData );
    addButtonData( addPermissionButtonData );
  }
  
  public List< ButtonData > getButtonDatas()
  {
    return buttonDatas;
  }

  protected void addButtonData( ButtonData buttonData )
  {
    buttonDatas.add( buttonData );
  }

  public ButtonData getSearchUserButtonData()
  {
    return searchUserButtonData;
  }
  @IResourceKey( inheritModuleName = true, moduleName = "um", className = "usermanagementpanel_searchuser" )
  public void setSearchUserButtonData( ButtonData searchUserButtonData )
  {
    this.searchUserButtonData = searchUserButtonData;
  }

  public ButtonData getSearchAccountButtonData()
  {
    return searchAccountButtonData;
  }
  @IResourceKey( inheritModuleName = true, moduleName = "um", className = "usermanagementpanel_searchaccount" )
  public void setSearchAccountButtonData( ButtonData searchAccountButtonData )
  {
    this.searchAccountButtonData = searchAccountButtonData;
  }

  public ButtonData getAddAccountButtonData()
  {
    return addAccountButtonData;
  }
  @IResourceKey( inheritModuleName = true, moduleName = "um", className = "usermanagementpanel_addaccount" )
  public void setAddAccountButtonData( ButtonData addAccountButtonData )
  {
    this.addAccountButtonData = addAccountButtonData;
  }

  public ButtonData getSearchRoleButtonData()
  {
    return searchRoleButtonData;
  }
  @IResourceKey( inheritModuleName = true, moduleName = "um", className = "usermanagementpanel_searchrole" )
  public void setSearchRoleButtonData( ButtonData searchRoleButtonData )
  {
    this.searchRoleButtonData = searchRoleButtonData;
  }

  public ButtonData getAddRoleButtonData()
  {
    return addRoleButtonData;
  }
  @IResourceKey( inheritModuleName = true, moduleName = "um", className = "usermanagementpanel_addrole" )
  public void setAddRoleButtonData( ButtonData addRoleButtonData )
  {
    this.addRoleButtonData = addRoleButtonData;
  }

  public ButtonData getAddPermissionButtonData()
  {
    return addPermissionButtonData;
  }
  @IResourceKey( inheritModuleName = true, moduleName = "um", className = "usermanagementpanel_addpermission" )
  public void setAddPermissionButtonData( ButtonData addPermissionButtonData )
  {
    this.addPermissionButtonData = addPermissionButtonData;
  }

  public void setButtonDatas( List< ButtonData > buttonDatas )
  {
    this.buttonDatas = buttonDatas;
  }
}
