package cg.usermanagement.gwt.server.resource;

import java.util.HashMap;
import java.util.Map;

import cg.common.property.IClassProperty;

public class ResourcePropertyKeyMapLookupStrategy implements IResourcePropertyKeyLookupStrategy
{
  // the property ==> key map
  private Map< IClassProperty, String > propertyKeyMap = new HashMap< IClassProperty, String >();
  
  @Override
  public String getResourceKey( IClassProperty resourceDataProperty )
  {
    return propertyKeyMap.get( resourceDataProperty );
  }

  public void registerPropertyKey( IClassProperty property, String key )
  {
    propertyKeyMap.put( property, key );
  }
}
