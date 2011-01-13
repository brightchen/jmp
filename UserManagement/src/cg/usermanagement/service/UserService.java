package cg.usermanagement.service;

import javax.persistence.Query;

import cg.service.dao.JpaDaoService;
import cg.usermanagement.api.model.IUser;
import cg.usermanagement.api.service.IUserService;

public class UserService extends JpaDaoService implements IUserService
{

  @Override
  public IUser findUser( String userId )
  {
    Query query = createQuery( "select u from User u where u.userId = :userId" );
    query.setParameter( "userId", userId );
    return (IUser)query.getSingleResult();
  }

}
