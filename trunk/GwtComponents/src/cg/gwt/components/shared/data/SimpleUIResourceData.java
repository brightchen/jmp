package cg.gwt.components.shared.data;

/*
 * simple resource data only has one value
 */
public class SimpleUIResourceData implements IUIResourceData
{
  private static final long serialVersionUID = -4643931729026828752L;

  private String value;

  public SimpleUIResourceData(){}
  
  public SimpleUIResourceData( String value )
  {
    setValue( value );
  }
  
  public String getValue()
  {
    return value;
  }

  public void setValue( String value )
  {
    this.value = value;
  }
  
  
}
