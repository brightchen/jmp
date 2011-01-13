package cg.service.dao;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cg.service.api.dao.IJpaDaoService;

public class JpaDaoService implements IJpaDaoService
{
  private EntityManager em;

  @PersistenceContext
  public void setEntityManager( EntityManager em )
  {
    this.em = em;
  }

  @Override
  public EntityManager getEntityManager()
  {
    if ( em == null )
      throw new IllegalStateException( "EntityManager has not been set on DAO before usage" );
    return em;
  }
  
  @Override
  public Query createQuery( String sql )
  {
    return getEntityManager().createQuery( sql );
  }

  @Override
  public Query createQuery( String sql, Map< String, Object > parameters )
  {
    Query query = getEntityManager().createQuery( sql );
    if( parameters != null && !parameters.isEmpty() )
    {
      for( Map.Entry< String, Object > entry : parameters.entrySet() )
      {
        query.setParameter( entry.getKey(), entry.getValue() );
      }
    }
    return query;
  }
  
}
