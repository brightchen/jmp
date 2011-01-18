package cg.uia.component;

import java.io.Serializable;

import cg.uia.api.IComponent;
import cg.uia.api.IComponentData;

public class Component< D extends IComponentData > implements IComponent< D >, Serializable
{
  private static final long serialVersionUID = -5150092691780215579L;

  private String id;
  private String name;
  private D data;

  @Override
  public String getId()
  {
    return id;
  }
  @Override
  public void setId( String id )
  {
    this.id = id;
  }

  @Override
  public String getName()
  {
    return name;
  }
  @Override
  public void setName( String name )
  {
    this.name = name;
  }
  @Override
  public D getData()
  {
    return data;
  }
  @Override
  public void setData( D data )
  {
    this.data = data;
  }

  @Override
  public boolean equals( Object other )
  {
    if( this == other )
      return true;
    if( !( other instanceof Component ) )
      return false;
    return getId().equals( ( (Component)other ).getId() );
  }

}
