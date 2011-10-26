package cg.usermanagement.gwt.client.user;

import java.io.Serializable;
import java.util.List;

import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.gwt.components.shared.data.ResponseData;
import cg.usermanagement.gwt.client.IUserManagement;
import cg.usermanagement.gwt.client.IUserManagementAsync;
import cg.usermanagement.gwt.client.UserManagementUIFlow;
import cg.usermanagement.gwt.shared.data.ListUsersData;
import cg.usermanagement.gwt.shared.data.SearchUserData;

import com.google.gwt.core.client.GWT;

public abstract class SearchUserEvent extends UIEvent< SearchUserData > implements Serializable
{
  private static final long serialVersionUID = 3941658703162105779L;

  private IUserManagementAsync userManagement = GWT.create( IUserManagement.class );
  
  @Override
  public void fire()
  {
    onSearchUser();
  }

  protected void onSearchUser()
  {
    final SearchUserData data = getData();
    userManagement.searchUser( data,
                               new PopupFailureReasonCallback< List< ResponseData< ListUsersData > > >()
                               {
                                 @Override
                                 public void onSuccess( List< ResponseData< ListUsersData > > searchUserResponse )
                                 {
                                   onSearchUserSuccess( searchUserResponse );
                                 }
                               } );
                            
  }
  
  protected void onSearchUserSuccess( List< ResponseData< ListUsersData > > searchUserResponse )
  {
    UserManagementUIFlow.onSearchUserSuccess( searchUserResponse );
  }
}
