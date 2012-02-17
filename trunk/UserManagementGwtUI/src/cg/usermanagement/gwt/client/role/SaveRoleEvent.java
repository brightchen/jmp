package cg.usermanagement.gwt.client.role;

import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.rpc.PopupResultCallback;
import cg.usermanagement.gwt.client.IUserManagement;
import cg.usermanagement.gwt.client.IUserManagementAsync;
import cg.usermanagement.gwt.shared.data.RoleDetailData;

import com.google.gwt.core.client.GWT;

public abstract class SaveRoleEvent extends UIEvent< RoleDetailData >
{
  private IUserManagementAsync userManagement = GWT.create( IUserManagement.class );
  
  @Override
  public void fire()
  {
    onSaveRole();
  }

  @SuppressWarnings( "unchecked")
  protected void onSaveRole()
  {
    final RoleDetailData data = getData();
    userManagement.addRole( data.getName(), PopupResultCallback.instance );
                            
  }
  
}
