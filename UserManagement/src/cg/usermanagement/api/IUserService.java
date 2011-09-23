package cg.usermanagement.api;

import java.util.List;
import java.util.Set;

import cg.usermanagement.model.Account;
import cg.usermanagement.model.Role;
import cg.usermanagement.model.User;
import cg.usermanagement.model.view.PermissionView;
import cg.usermanagement.model.view.RoleView;
import cg.usermanagement.model.view.UserRegisterView;
import cg.usermanagement.model.view.UserSearchView;
import cg.usermanagement.shared.LoginException;
import cg.usermanagement.shared.RoleException;

public interface IUserService
{
  public Set< PermissionView > userLogin( String userName, String password ) throws LoginException;
  public Set< PermissionView > accountLogin( String accountName, String password ) throws LoginException;
  
  public User findUserByName( String name );
  public List< UserSearchView > findUsers( UserSearchCriteria criteria );
  
  public Account findAccountByName( String accountId );
  public boolean registerUser( UserRegisterView userRegisterView );
  
  public Set< PermissionView > getPermissionsByUser( long userId );
  public Set< PermissionView > getPermissionsByAccount( long accountId );
  
  public RoleView addRole( String roleName ) throws RoleException;
  public Role findRoleByName( String roleName );
}
