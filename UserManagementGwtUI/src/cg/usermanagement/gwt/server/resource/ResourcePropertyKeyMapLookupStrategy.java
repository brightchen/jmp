package cg.usermanagement.gwt.server.resource;

import java.util.HashMap;
import java.util.Map;

public class ResourcePropertyKeyMapLookupStrategy implements IResourcePropertyKeyLookupStrategy
{
  // the property ==> key map
  private Map< IResourceDataProperty, String > propertyKeyMap = new HashMap< IResourceDataProperty, String >();
  
  @Override
  public String getResourceKey( IResourceDataProperty resourceDataProperty )
  {
    return propertyKeyMap.get( resourceDataProperty );
  }

  public void registerPropertyKey( IResourceDataProperty property, String key )
  {
    propertyKeyMap.put( property, key );
  }
}
