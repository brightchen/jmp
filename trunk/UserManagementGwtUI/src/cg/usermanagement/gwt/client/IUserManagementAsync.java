package cg.usermanagement.gwt.client;

import cg.usermanagement.gwt.shared.data.UserRegisterData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IUserManagementAsync
{
  //the async version should not throw any exception
  public void userlogin( String userName, String password, AsyncCallback< Void > callback );
  public void accountlogin( String accountName, String password, AsyncCallback< Void > callback  );
  
  public void registerUser( UserRegisterData data, AsyncCallback< Void > callback );
  
  /*
   * the add entity need return the id of the entity
   */
  public void addRole( String roleName, AsyncCallback< Long > callback );
}
