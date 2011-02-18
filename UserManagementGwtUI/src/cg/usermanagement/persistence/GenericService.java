package cg.usermanagement.persistence;

import java.util.List;

import javax.persistence.NoResultException;

import cg.model.api.IEntity;
import cg.model.api.INamedEntity;
import cg.usermanagement.api.service.IGenericService;

public class GenericService implements IGenericService
{

  @Override
  @SuppressWarnings( "unchecked" )
  public < T extends IEntity > T findEntityById( Class< T > entityClass, Long id )
  {
    String hsql = String.format( "select o from %s o where o.id = %d", entityClass.getName(), id );
    try
    {
      return (T)PersistenceManager.getPersistenceEntityManager().createQuery( hsql  ).getSingleResult();
    }
    catch( NoResultException noResult )
    {
      return null;
    }
  }

  //precondition: the entity's name field is name
  @Override
  @SuppressWarnings( "unchecked" )
  public < T extends INamedEntity > List< T > findEntityByName( Class< T > entityClass, String name )
  {
    String hsql = String.format( "select o from %s o where o.name = \'%s\'", entityClass.getName(), name );
    try
    {
      return (List<T>)PersistenceManager.getPersistenceEntityManager().createQuery( hsql  ).getResultList();
    }
    catch( NoResultException noResult )
    {
      return null;
    }
  }

}
