package cg.usermanagement.persistence;

import javax.persistence.NoResultException;

import cg.usermanagement.api.model.IAccount;
import cg.usermanagement.api.model.IUser;
import cg.usermanagement.api.service.IUserService;
import cg.usermanagement.model.Account;
import cg.usermanagement.model.User;

public class UserService implements IUserService
{
  @Override
  public IUser findUserByName( String name )
  {
    String hsql = String.format( "select u from %s u where u.name = \'%s\'", User.class.getName(), name );
    try
    {
      return (User)PersistenceManager.getPersistenceEntityManager().createQuery( hsql  ).getSingleResult();
    }
    catch( NoResultException noResult )
    {
      return null;
    }
  }

  @Override
  public IAccount findAccountByAccountId( String accountId )
  {
    String hsql = String.format( "select a from %s a where a.accountId = \'%s\'", Account.class.getName(), accountId );
    try
    {
      return (Account)PersistenceManager.getPersistenceEntityManager().createQuery( hsql  ).getSingleResult();
    }
    catch( NoResultException noResult )
    {
      return null;
    }
  }

}
