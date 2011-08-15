package cg.resourcemanagement;

import java.util.HashMap;
import java.util.Map;

import cg.common.property.ClassProperty;

//FIXME: the strategy has problem, the key should be ( ClassProperty, resourceOwnerClass )
public class ResourcePropertyKeyMapLookupStrategy implements IResourcePropertyKeyLookupStrategy
{
  // the property ==> key map
  
  private Map< ClassProperty, ResourceKey > propertyKeyMap = new HashMap< ClassProperty, ResourceKey >();
  
  @Override
  public ResourceKey getResourceKey( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    return propertyKeyMap.get( resourceDataProperty );
  }

  public void registerPropertyKey( ClassProperty property, ResourceKey key )
  {
    propertyKeyMap.put( property, key );
  }
}
