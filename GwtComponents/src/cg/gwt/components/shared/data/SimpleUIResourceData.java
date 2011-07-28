package cg.gwt.components.shared.data;

import java.io.Serializable;

/*
 * simple resource data only has one value
 */
public class SimpleUIResourceData extends ResourceData implements Serializable
{
  private static final long serialVersionUID = -4643931729026828752L;

  private String value;

  public SimpleUIResourceData(){}
  
  public SimpleUIResourceData( String value )
  {
    setValue( value );
  }
  
  /*
   * the value is a generic property can shouldn't inject the resource value. so declare the accessible as protected
   * the sub class's use this method to set value
   */
  protected String getValue()
  {
    return value;
  }

  protected void setValue( String value )
  {
    this.value = value;
  }
  
  
}
