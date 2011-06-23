package cg.usermanagement.gwt.client.user;

import java.util.List;

import com.google.gwt.core.client.GWT;

import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.usermanagement.gwt.client.IUserManagement;
import cg.usermanagement.gwt.client.IUserManagementAsync;
import cg.usermanagement.gwt.client.UserManagementUIFlow;
import cg.usermanagement.gwt.shared.data.AddRoleData;
import cg.usermanagement.gwt.shared.data.SearchUserData;
import cg.usermanagement.gwt.shared.data.UserListData;

public class SearchUserEvent extends UIEvent< SearchUserData >
{
  private IUserManagementAsync userManagement = GWT.create( IUserManagement.class );
  
  @Override
  public void fire()
  {
    onSearchUser();
  }

  protected void onSearchUser()
  {
    final SearchUserData data = getData();
    userManagement.searchUser( data
                             new PopupFailureReasonCallback< Long >()
                             {
                               @Override
                               public void onSuccess( List< UserListData > users )
                               {
                                 onSearchUserSuccess( users );
                               }
                             } );
                            
  }
  
  protected void onSearchUserSuccess( List< UserListData > users )
  {
  }
}
