package cg.model.api;

/*
 * the model view can be generated via it's entity
 * sometime, the model view represents multiple entities, but only one entity should be the main entity
 * the view and it's main entity have the same identity
 * the IReadableModelView only support fetch data from entity.
 * the way of implementation of IReadableModelView and IWritableModelView can be different.
 *   - IReadableModelView supposes to cache the fields of the sub lazy loading entities
 *   - IWritableModelView can delegate the data to the entities  
 */  
public interface IReadableModelView< E extends IEntity > extends IEntityAware< E >
{
  public void getValuesFromEntity();
}
