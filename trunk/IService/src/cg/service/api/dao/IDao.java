package cg.service.api.dao;

import java.util.List;

import cg.model.api.IEntity;

public interface IDao< T extends IEntity >
{
  public T findById( long id );

  public List< T > findAll();

  public void save( T entity );

  public void remove( long id );
}
