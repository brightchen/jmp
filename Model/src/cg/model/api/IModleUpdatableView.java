package cg.model.api;

/*
 * IModleUpdatableView support update value from view to the entity
 */
public interface IModleUpdatableView< E extends IEntity > extends IModelView< E >
{
  /*
   * in most case, the get method of view can delegate to the entity's corresponding get method.
   * only the lazy loading object of entity should be kept in the view
   */
  public void setValuesToEntity();
}
