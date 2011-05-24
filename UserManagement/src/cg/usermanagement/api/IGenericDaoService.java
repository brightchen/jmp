package cg.usermanagement.api;

import java.util.List;

import cg.model.api.IEntity;
import cg.model.api.INamedEntity;

public interface IGenericDaoService
{
  public <T extends IEntity> T findEntityById( Class<T> entityClass, Long id );
  public <T extends INamedEntity> List<T> findEntityByName( Class<T> entityClass, String name );
  
  public < T extends IEntity > void saveEntity( T entity );
  public < T extends IEntity > void removeEntityById( Class< T > entityClass, Long id );

}
