package cg.model.api;

// typical entity is a type of entity which has name
public interface INamedEntity extends IEntity
{
  public String getName();
  public void setName( String name );
}
