package cg.usermanagement.gwt.client;

import java.util.List;

import cg.gwt.components.shared.data.ResponseData;
import cg.usermanagement.gwt.shared.data.SearchUserData;
import cg.usermanagement.gwt.shared.data.UserListData;
import cg.usermanagement.gwt.shared.data.UserRegisterData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IUserManagementAsync
{
  public void getStartUI( String localeName, AsyncCallback< List< ResponseData<?> > > callback );
  
  //the async version should not throw any exception
  public void userlogin( String userName, String password, AsyncCallback< Void > callback );
  public void accountlogin( String accountName, String password, AsyncCallback< Void > callback  );
  
  public void registerUser( UserRegisterData data, AsyncCallback< Void > callback );
  
  /*
   * the add entity need return the id of the entity
   */
  public void addRole( String roleName, AsyncCallback< Long > callback );
  
  public void searchUser( SearchUserData searchUserData, AsyncCallback< List< UserListData > > userListDatas );
}
