package cg.usermanagement.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.transaction.annotation.Transactional;

import cg.usermanagement.api.IUserService;
import cg.usermanagement.model.Account;
import cg.usermanagement.model.User;
import cg.usermanagement.model.view.UserRegisterView;

@Transactional
public class UserService extends GenericJpaDaoService implements IUserService
{
  @Override
  public User findUserByName( String name )
  {
    String hsql = String.format( "select u from %s u where u.name = \'%s\'", User.class.getName(), name );
    try
    {
      return (User)getEntityManager().createQuery( hsql  ).getSingleResult();
    }
    catch( NoResultException noResult )
    {
      return null;
    }
  }

  @Override
  public Account findAccountByName( String name )
  {
    String hsql = String.format( "select a from %s a where a.name = \'%s\'", Account.class.getName(), name );
    try
    {
      return (Account)getEntityManager().createQuery( hsql  ).getSingleResult();
    }
    catch( NoResultException noResult )
    {
      return null;
    }
  }

  @Transactional
  @Override
  public boolean registerUser( UserRegisterView userRegisterView )
  {
    userRegisterView.setValuesToEntity();
    User user = userRegisterView.getEntity();
    EntityManager em = getEntityManager();
    em.persist( user );
    return true;
  }
}
