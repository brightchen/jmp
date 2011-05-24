package cg.usermanagement.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import cg.model.api.IEntity;
import cg.model.api.INamedEntity;
import cg.usermanagement.api.IGenericDaoService;

public class GenericJpaDaoService implements IGenericDaoService
{
  private EntityManager entityManager;
  
  @PersistenceContext
  public void setEntityManager(EntityManager entityManager) 
  {
    this.entityManager = entityManager;
  }

  protected EntityManager getEntityManager()
  {
    return entityManager;
  }
  
  @Override
  @SuppressWarnings( "unchecked" )
  public < T extends IEntity > T findEntityById( Class< T > entityClass, Long id )
  {
    String hsql = String.format( "select o from %s o where o.id = %d", entityClass.getName(), id );
    try
    {
      return (T)getEntityManager().createQuery( hsql  ).getSingleResult();
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
      return (List<T>)getEntityManager().createQuery( hsql  ).getResultList();
    }
    catch( NoResultException noResult )
    {
      return null;
    }
  }

  @Override
  public < T extends IEntity > void saveEntity( T entity )
  {
    Long id = entity.getId();
    if (id == null || findEntityById(entity.getClass(), id) == null) 
      getEntityManager().persist(entity);
    else getEntityManager().merge(entity);
  }

  @Override
  public <T extends IEntity > void removeEntityById( Class< T > entityClass, Long id )
  {
    T entity = findEntityById( entityClass, id );
    if( entity == null )
      return;
    getEntityManager().remove( entity );
  }

}
