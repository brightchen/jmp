package cg.persistence.bmp;

import java.util.List;

import cg.model.api.IEntity;
import cg.persistence.api.IPersistenceService;

public class PersistenceServiceBmpImpl implements IPersistenceService
{
  @Override
  public < T extends IEntity > T find( Class< T > entityClass, long id )
  {
    // TODO Auto-generated method stub
    return null;
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

}
