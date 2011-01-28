package cg.usermanagement.persistence;

import javax.persistence.NoResultException;

import cg.usermanagement.api.model.IUser;
import cg.usermanagement.api.service.IUserService;
import cg.usermanagement.model.UserBean;

public class UserService implements IUserService
{
  @Override
  public IUser findUserByName( String name )
  {
    String hsql = String.format( "select u from %s u where u.name = \'%s\'", UserBean.class.getName(), name );
    try
    {
      return (UserBean)PersistenceManager.getPersistenceEntityManager().createQuery( hsql  ).getSingleResult();
    }
    catch( NoResultException noResult )
    {
      return null;
    }
  }

}
