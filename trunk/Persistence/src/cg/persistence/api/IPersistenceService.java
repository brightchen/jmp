package cg.persistence.api;

import java.util.List;

import cg.model.api.IEntity;

public interface IPersistenceService
{
  // find and retrieve the entity by id
  public < T extends IEntity > T find( Class<T> entityClass, long id );

  // retrieve all entities
  public < T extends IEntity > List<T> findAll( Class<T> entityClass );

  public < T extends IEntity >  void save( T entity );

  public < T extends IEntity > void remove( T entity );
  public < T extends IEntity > void remove( Class<T> entityClass, long id );
}
