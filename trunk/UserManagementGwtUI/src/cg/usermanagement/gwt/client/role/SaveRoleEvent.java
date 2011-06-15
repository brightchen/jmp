package cg.usermanagement.gwt.client.role;

import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.usermanagement.gwt.client.IUserManagement;
import cg.usermanagement.gwt.client.IUserManagementAsync;
import cg.usermanagement.gwt.shared.data.RoleData;

import com.google.gwt.core.client.GWT;

public abstract class SaveRoleEvent extends UIEvent< RoleData >
{
  private IUserManagementAsync userManagement = GWT.create( IUserManagement.class );
  
  @Override
  public void fire()
  {
    onSaveRole();
  }

  protected void onSaveRole()
  {
    final RoleData data = getData();
    userManagement.saveRole( data.getId(), data.getName(),
                             new PopupFailureReasonCallback< Void >()
                             {
                               @Override
                               public void onSuccess( Void returned )
                               {
                                 onSaveRoleSuccess( data );
                               }
                             } );
                            
  }
  
  protected void onSaveRoleSuccess( RoleData data )
  {
    
  }
}
