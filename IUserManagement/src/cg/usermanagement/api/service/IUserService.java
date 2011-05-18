package cg.usermanagement.api.service;

import cg.usermanagement.api.model.IAccount;
import cg.usermanagement.api.model.IUser;

public interface IUserService
{
  public IUser findUserByName( String name );
  public IAccount findAccountByAccountId( String accountId );
  public boolean registerUser( UserRegisterData data );
}
