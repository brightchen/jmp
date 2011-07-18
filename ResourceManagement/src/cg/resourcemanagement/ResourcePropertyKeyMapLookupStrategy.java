package cg.resourcemanagement;

import java.util.HashMap;
import java.util.Map;

import cg.common.property.ClassProperty;

public class ResourcePropertyKeyMapLookupStrategy implements IResourcePropertyKeyLookupStrategy
{
  // the property ==> key map
  private Map< ClassProperty, String > propertyKeyMap = new HashMap< ClassProperty, String >();
  
  @Override
  public String getResourceKey( ClassProperty resourceDataProperty )
  {
    return propertyKeyMap.get( resourceDataProperty );
  }

  public void registerPropertyKey( ClassProperty property, String key )
  {
    propertyKeyMap.put( property, key );
  }
}
