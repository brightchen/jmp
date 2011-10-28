package cg.usermanagement.gwt.client.user;

import java.io.Serializable;

import cg.gwt.components.client.ui.event.UIEvent;
import cg.usermanagement.gwt.client.IUserManagement;
import cg.usermanagement.gwt.client.IUserManagementAsync;
import cg.usermanagement.gwt.shared.data.SearchUserData;
import cg.usermanagement.gwt.shared.rpc.UserManagementTypicalCallback;

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
    userManagement.searchUser( data, UserManagementTypicalCallback.instance );
                            
  }
}
