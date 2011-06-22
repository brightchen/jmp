package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.event.UIEvent;

public class UserManagementButtonEvent extends UIEvent< UserManagementButtonMeta >
{
  private UserManagementButtonMeta data;
  
  @Override
  public void fire()
  {
    if( UserManagementButtonMeta.SEARCH_USER.equals( data ) )
    {
      UserManagementUIFlow.doSearchUser();
      return;
    }
    if( UserManagementButtonMeta.SEARCH_ACCOUNT.equals( data ) )
    {
      UserManagementUIFlow.doSearchAccount();
      return;
    }
    if( UserManagementButtonMeta.ADD_ACCOUNT.equals( data ) )
    {
      UserManagementUIFlow.doAddAccount();
      return;
    }
    if( UserManagementButtonMeta.SEARCH_ROLE.equals( data ) )
    {
      UserManagementUIFlow.doSearchRole();
      return;
    }

    if( UserManagementButtonMeta.ADD_ROLE.equals( data ) )
    {
      UserManagementUIFlow.doAddRole();
      return;
    }
    if( UserManagementButtonMeta.ADD_PERMISSION.equals( data ) )
    {
      return;
    }
  }
  
  @Override
  public UserManagementButtonMeta getData()
  {
    return data;
  }

  /*
   * this event don't need dynamic data from ui. So, we can use setData
   * the data here is just for distinguishing which UserManagement button triggered this event. 
   */
  public void setData( UserManagementButtonMeta data )
  {
    this.data = data;
  }
  

}
