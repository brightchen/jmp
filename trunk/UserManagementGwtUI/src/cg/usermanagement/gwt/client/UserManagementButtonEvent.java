package cg.usermanagement.gwt.client;

import cg.usermanagement.gwt.shared.data.UserManagementPanelOperation;
import cg.usermanagement.gwt.shared.rpc.UserManagementTypicalCallback;

public class UserManagementButtonEvent extends UserManagementEvent< UserManagementPanelOperation >
{
  private UserManagementPanelOperation data;
  
  @Override
  public void fire()
  {
    getUserManagement().onUserManagementPanelOperation( data, UserManagementTypicalCallback.instance );
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
