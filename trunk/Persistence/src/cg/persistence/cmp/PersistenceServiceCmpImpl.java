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
    Query query = getEntityManager().createQuery( String.format( "FROM %s a WHERE a.id = %d", entityClass.getName(), id ) );
    List results = query.getResultList();
    return results == null ? null : (T)results.get( 0 );
  }

  @Override
  public < T extends IEntity > List< T > findAll( Class< T > entityClass )
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public < T extends IEntity > void save( T entity )
  {
    // TODO Auto-generated method stub
    
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
