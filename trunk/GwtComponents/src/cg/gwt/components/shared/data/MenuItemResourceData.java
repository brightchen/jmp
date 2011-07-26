package cg.gwt.components.shared.data;

import java.io.Serializable;

public class MenuItemResourceData extends SimpleUIResourceData implements Serializable
{
  private static final long serialVersionUID = 7376970165645552125L;

  public MenuItemResourceData(){}
  
  public MenuItemResourceData( String title )
  {
    super( title );
  }
  
  public String getTitle()
  {
    return super.getValue();
  }
  
  public void setTitle( String title )
  {
    super.setValue( title );
  }
  
}
