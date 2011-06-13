package cg.usermanagement.api;

import java.util.Set;

import cg.usermanagement.model.Account;
import cg.usermanagement.model.User;
import cg.usermanagement.model.view.PermissionView;
import cg.usermanagement.model.view.UserRegisterView;
import cg.usermanagement.shared.LoginException;

public interface IUserService
{
  public Set< PermissionView > userLogin( String userName, String password ) throws LoginException;
  public Set< PermissionView > accountLogin( String accountName, String password ) throws LoginException;
  
  public User findUserByName( String name );
  public Account findAccountByName( String accountId );
  public boolean registerUser( UserRegisterView userRegisterView );
  
  public Set< PermissionView > getPermissionsByUser( long userId );
  public Set< PermissionView > getPermissionsByAccount( long accountId );
}
