package cg.resourcemanagement;

import java.util.HashMap;
import java.util.Map;

import cg.common.property.ClassProperty;

//FIXME: the strategy has problem, the key should be ( ClassProperty, resourceOwnerClass )
public class ResourcePropertyKeyMapLookupStrategy implements IResourcePropertyKeyLookupStrategy
{
  // the property ==> key map
  
  private Map< ClassProperty, String > propertyKeyMap = new HashMap< ClassProperty, String >();
  
  @Override
  public String getResourceKey( ClassProperty resourceDataProperty, Class<?> resourceOwnerClass )
  {
    return propertyKeyMap.get( resourceDataProperty );
  }

  public void registerPropertyKey( ClassProperty property, String key )
  {
    propertyKeyMap.put( property, key );
  }
}
