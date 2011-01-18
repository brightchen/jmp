package cg.uia.api;

public interface IComponent< D extends IComponentData >
{
  //each component has unique id
  public String getId();
  public void setId( String id );
  
  public String getName();
  public void setName( String name );
  
  public D getData();
  public void setData( D data );
}
