package cg.service.api.dao;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public interface IJpaDaoService
{
  public EntityManager getEntityManager();
  public Query createQuery( String sql );
  public Query createQuery( String sql, Map< String, Object > parameters );
}
