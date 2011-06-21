package cg.usermanagement.gwt.client.role;

import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.usermanagement.gwt.client.IUserManagement;
import cg.usermanagement.gwt.client.IUserManagementAsync;
import cg.usermanagement.gwt.client.UserManagementUIFlow;
import cg.usermanagement.gwt.shared.data.AddRoleData;

import com.google.gwt.core.client.GWT;

public abstract class AddRoleEvent extends UIEvent< AddRoleData >
{
  private IUserManagementAsync userManagement = GWT.create( IUserManagement.class );
  
  @Override
  public void fire()
  {
    onAddRole();
  }

  protected void onAddRole()
  {
    final AddRoleData data = getData();
    userManagement.addRole( data.getName(),
                             new PopupFailureReasonCallback< Void >()
                             {
                               @Override
                               public void onSuccess( Void returned )
                               {
                                 onAddRoleSuccess( data );
                               }
                             } );
                            
  }
  
  protected void onAddRoleSuccess( AddRoleData data )
  {
    UserManagementUIFlow.onAddRoleSuccess( data );
  }
}
