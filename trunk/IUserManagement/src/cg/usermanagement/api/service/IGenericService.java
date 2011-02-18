package cg.usermanagement.api.service;

import java.util.List;

import cg.model.api.IEntity;
import cg.model.api.INamedEntity;

public interface IGenericService
{
  public <T extends IEntity> T findEntityById( Class<T> entityClass, Long id );
  public <T extends INamedEntity> List<T> findEntityByName( Class<T> entityClass, String name );
}
