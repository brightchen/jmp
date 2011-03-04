package cg.persistence.cmp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cg.model.api.IEntity;
import cg.persistence.api.IPersistenceService;

public class PersistenceServiceCmpImpl implements IPersistenceService
{
  //specify unit of work?
  @PersistenceContext
  private EntityManager entityManager;
  
  @Override
  public < T extends IEntity > T find( Class< T > entityClass, long id )
  {
    return getEntityManager().find( entityClass, id );
  }

  @Override
  @SuppressWarnings( "unchecked" )
  public < T extends IEntity > List< T > findAll( Class< T > entityClass )
  {
    Query query = getEntityManager().createQuery( String.format( "SELECT object(a) FROM %s a", entityClass.getName() ) );
    return (List<T>)query.getResultList();
  }

  @Override
  public < T extends IEntity > void save( T entity )
  {
    getEntityManager().merge( entity );
  }

  @Override
  public < T extends IEntity > void remove( T entity )
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public < T extends IEntity > void remove( Class< T > entityClass, long id )
  {
    // TODO Auto-generated method stub
    
  }

  public EntityManager getEntityManager()
  {
    return entityManager;
  }

  public void setEntityManager( EntityManager entityManager )
  {
    this.entityManager = entityManager;
  }

  
}
