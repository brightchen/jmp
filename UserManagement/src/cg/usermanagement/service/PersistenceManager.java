package cg.usermanagement.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersistenceManager
{
  private static PersistenceManager instance;
  
  public PersistenceManager(){};
  
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
  
  private EntityManager entityManager;

  public static EntityManager getPersistenceEntityManager()
  {
    return getInstance().entityManager;
  }

  public static void setPersistenceEntityManager( EntityManager entityManager )
  {
    getInstance().entityManager = entityManager;
  }

  public EntityManager getEntityManager()
  {
    return entityManager;
  }

  @PersistenceContext
  public void setEntityManager( EntityManager entityManager )
  {
    if( instance == null )
      instance = this;
    this.entityManager = entityManager;
  }
   
}
