package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.event.UIEvent;

public class UserManagementButtonEvent extends UIEvent< UserManagementButtonEvent.Operation >
{
  public static enum Operation
  {
    SearchUser,
    SearchAccount,
    AddAccount,
    SearchRole,
    AddRole,
    AddPermission
  }
  private Operation data;
  
  @Override
  public void fire()
  {
    if( Operation.SearchUser.equals( data ) )
    {
      UserManagementUIFlow.doSearchUser();
      return;
    }
    if( Operation.SearchAccount.equals( data ) )
    {
      UserManagementUIFlow.doSearchAccount();
      return;
    }
    if( Operation.AddAccount.equals( data ) )
    {
      UserManagementUIFlow.doAddAccount();
      return;
    }
    if( Operation.SearchRole.equals( data ) )
    {
      UserManagementUIFlow.doSearchRole();
      return;
    }

    if( Operation.AddRole.equals( data ) )
    {
      UserManagementUIFlow.doAddRole();
      return;
    }
    if( Operation.AddPermission.equals( data ) )
    {
      return;
    }
  }
  
  @Override
  public Operation getData()
  {
    return data;
  }

  /*
   * this event don't need dynamic data from ui. So, we can use setData
   * the data here is just for distinguishing which UserManagement button triggered this event. 
   */
  public void setData( Operation data )
  {
    this.data = data;
  }
  

}
