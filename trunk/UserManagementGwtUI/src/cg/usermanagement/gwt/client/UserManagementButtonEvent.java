package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.event.UIEvent;
import cg.usermanagement.gwt.shared.data.UserManagementPanelOperation;

public class UserManagementButtonEvent extends UIEvent< UserManagementPanelOperation >
{
  private UserManagementPanelOperation data;
  
  @Override
  public void fire()
  {
    if( UserManagementPanelOperation.SearchUser.equals( data ) )
    {
      UserManagementUIFlow.doSearchUser();
      return;
    }
    if( UserManagementPanelOperation.SearchAccount.equals( data ) )
    {
      UserManagementUIFlow.doSearchAccount();
      return;
    }
    if( UserManagementPanelOperation.AddAccount.equals( data ) )
    {
      UserManagementUIFlow.doAddAccount();
      return;
    }
    if( UserManagementPanelOperation.SearchRole.equals( data ) )
    {
      UserManagementUIFlow.doSearchRole();
      return;
    }

    if( UserManagementPanelOperation.AddRole.equals( data ) )
    {
      UserManagementUIFlow.doAddRole();
      return;
    }
    if( UserManagementPanelOperation.AddPermission.equals( data ) )
    {
      return;
    }
  }
  
  @Override
  public UserManagementPanelOperation getData()
  {
    return data;
  }

  /*
   * this event don't need dynamic data from ui. So, we can use setData
   * the data here is just for distinguishing which UserManagement button triggered this event. 
   */
  public void setData( UserManagementPanelOperation data )
  {
    this.data = data;
  }
  

}
