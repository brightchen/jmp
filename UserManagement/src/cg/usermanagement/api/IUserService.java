package cg.usermanagement.api;

import cg.usermanagement.model.Account;
import cg.usermanagement.model.User;
import cg.usermanagement.model.view.UserRegisterView;

public interface IUserService
{
  public User findUserByName( String name );
  public Account findAccountByName( String accountId );
  public boolean registerUser( UserRegisterView userRegisterView );
}
