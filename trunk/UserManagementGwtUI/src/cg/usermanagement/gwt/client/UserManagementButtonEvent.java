package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.event.UIEvent;

public class UserManagementButtonEvent extends UIEvent< UserManagementButtonMeta >
{
  private UserManagementButtonMeta data;
  
  @Override
  public void fire()
  {
    
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
