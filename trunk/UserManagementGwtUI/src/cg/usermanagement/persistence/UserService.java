package cg.usermanagement.persistence;

import cg.usermanagement.api.model.IUser;
import cg.usermanagement.api.service.IUserService;
import cg.usermanagement.model.User;

public class UserService implements IUserService
{
  @Override
  public IUser findUserByName( String name )
  {
    return (User)PersistenceManager.getPersistenceEntityManager().createQuery( String.format( "select object(o) from %s where o.name = %s", User.class.getName(), name ) ).getSingleResult();
  }

}
