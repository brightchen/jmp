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

/*
 * for each request, return the response datas which required to build the next UI
 * by this way, the web server side can control the UI flow completely
 */
@RemoteServiceRelativePath( "UserManagement")
public interface IUserManagement extends RemoteService
{
  /*
   * return the information of Control Section UI and Client Section Start UI
   */
  public List< ResponseData<?> > getStartUI( String localeName );
  
  public List< ResponseData<?> > changeLocale( String localeName );
  
  public List< ResponseData<?> > userlogin( String userName, String password ) throws LoginException;
  public List< ResponseData<?> > accountlogin( String accountName, String password ) throws LoginException;
  
  public void registerUser( UserRegisterData data ) throws RegisterUserException;
  
  public long addRole( String roleName ) throws RoleException;
  
  public List< UserListData > searchUser( SearchUserData searchUserData );
}
