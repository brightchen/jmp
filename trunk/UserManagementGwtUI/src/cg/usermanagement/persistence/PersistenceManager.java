package cg.usermanagement.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersistenceManager
{
  private static PersistenceManager instance;
  
  private PersistenceManager(){};
  
  public static PersistenceManager getInstance()
  {
    if( instance == null )
    {
      synchronized( PersistenceManager.class )
      {
        if( instance == null )
          instance = new PersistenceManager();
      }
    }
    return instance;
  }
  
  @PersistenceContext
  private EntityManager entityManager;

  public static EntityManager getEntityManager()
  {
    return getInstance().entityManager;
  }

  public static void setEntityManager( EntityManager entityManager )
  {
    getInstance().entityManager = entityManager;
  }
   
}
