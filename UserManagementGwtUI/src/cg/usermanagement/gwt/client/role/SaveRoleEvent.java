package cg.usermanagement.gwt.client.role;

import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.usermanagement.gwt.client.IUserManagement;
import cg.usermanagement.gwt.client.IUserManagementAsync;
import cg.usermanagement.gwt.client.UserManagementUIFlow;
import cg.usermanagement.gwt.shared.data.RoleDetailData;

import com.google.gwt.core.client.GWT;

public abstract class SaveRoleEvent extends UIEvent< RoleDetailData >
{
  private IUserManagementAsync userManagement = GWT.create( IUserManagement.class );
  
  @Override
  public void fire()
  {
    onAddRole();
  }

  protected void onAddRole()
  {
    final RoleDetailData data = getData();
    userManagement.addRole( data.getName(),
                             new PopupFailureReasonCallback< Long >()
                             {
                               @Override
                               public void onSuccess( Long roleId )
                               {
                                 data.setId( roleId );
                                 onSaveRoleSuccess( data );
                               }
                             } );
                            
  }
  
  protected void onSaveRoleSuccess( RoleDetailData data )
  {
    UserManagementUIFlow.onSaveRoleSuccess( data );
  }
}
