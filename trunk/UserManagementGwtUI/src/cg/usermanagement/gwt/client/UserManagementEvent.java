package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.event.UIEvent;

import com.google.gwt.core.client.GWT;

public abstract class UserManagementEvent< D > extends UIEvent< D >
{
  private IUserManagementAsync userManagement = GWT.create( IUserManagement.class );
  
  protected IUserManagementAsync getUserManagement()
  {
    return userManagement;
  }

}
