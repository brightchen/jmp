package cg.gwt.components.shared.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuEventData implements Serializable
{
  private static final long serialVersionUID = -5759458150561820184L;
  
  private String key;
  private List< String > parameters;

  public MenuEventData(){}
  
  public MenuEventData( String key )
  {
    setKey( key );
  }
  
  public MenuEventData( String key, String ... parameters )
  {
    setKey( key );
    addParameters( parameters );
  }

  public String getKey()
  {
    return key;
  }

  public void setKey( String key )
  {
    this.key = key;
  }

  public List< String > getParameters()
  {
    return parameters;
  }

  public void addParameters( String ... parameters )
  {
    if( parameters == null )
      return;
    if( this.parameters == null )
      this.parameters = new ArrayList< String >();
    for( String parameter : parameters )
    {
      this.parameters.add( parameter );
    }
  }
  
  public void setParameters( List< String > parameters )
  {
    this.parameters = parameters;
  }
}
