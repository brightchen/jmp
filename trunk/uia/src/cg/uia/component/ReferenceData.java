package cg.uia.component;

import java.io.Serializable;

import cg.uia.api.IComponentData;

// the component is implemented by wrapper and provide setValue/getValue
public class ReferenceData< D > implements IComponentData, Serializable
{
  private static final long serialVersionUID = 2822176704275203324L;
  
  private D value;
  
  public ReferenceData( D value )
  {
    setValue( value );
  }

  public D getValue()
  {
    return value;
  }
  public void setValue( D value )
  {
    this.value = value;
  }
  
}
