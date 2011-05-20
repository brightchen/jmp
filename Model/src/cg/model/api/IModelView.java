package cg.model.api;

/*
 * the model view can be generated via it's entity
 * sometime, the model view represents multiple entities, but only one entity should be the main entity
 * the view and it's main entity have the same identity
 * the IModelView only support fetch data from entity, it does NOT support update data to the entity
 */
public interface IModelView< E extends IEntity > extends IEntityAware< E >
{
  public void getValuesFromEntity();
}
