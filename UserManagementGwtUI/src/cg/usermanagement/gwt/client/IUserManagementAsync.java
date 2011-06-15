package cg.usermanagement.gwt.client;

import cg.usermanagement.gwt.shared.data.UserRegisterData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IUserManagementAsync
{
  //the async version should not throw any exception
  public void userlogin( String userName, String password, AsyncCallback< Void > callback );
  public void accountlogin( String accountName, String password, AsyncCallback< Void > callback  );
  
  public void registerUser( UserRegisterData data, AsyncCallback< Void > callback );
  
  public void saveRole( Long roleId, String roleName, AsyncCallback< Void > callback  );
}
