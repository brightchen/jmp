package cg.usermanagement.gwt.client;

import java.util.List;

import cg.gwt.components.shared.data.ResponseData;
import cg.usermanagement.gwt.shared.data.SearchUserData;
import cg.usermanagement.gwt.shared.data.UserListData;
import cg.usermanagement.gwt.shared.data.UserRegisterData;
import cg.usermanagement.shared.LoginException;
import cg.usermanagement.shared.RegisterUserException;
import cg.usermanagement.shared.RoleException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath( "UserManagement")
public interface IUserManagement extends RemoteService
{
  public ResponseData<?> getStartUI();
  
  public void userlogin( String userName, String password ) throws LoginException;
  public void accountlogin( String accountName, String password ) throws LoginException;
  
  public void registerUser( UserRegisterData data ) throws RegisterUserException;
  
  public long addRole( String roleName ) throws RoleException;
  
  public List< UserListData > searchUser( SearchUserData searchUserData );
}
