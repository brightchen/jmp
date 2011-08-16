package cg.resourcemanagement;

import java.util.HashMap;
import java.util.Map;

import cg.common.property.ClassProperty;

//FIXME: the strategy has problem, the key should be take ResourcePropertyContext into consideration
public class ResourcePropertyKeyCacheLookupStrategy implements IResourcePropertyKeyLookupStrategy
{
  // the property ==> key map
  private Map< ClassProperty, ResourceKey > propertyKeyMap = new HashMap< ClassProperty, ResourceKey >();

  private static ResourcePropertyKeyCacheLookupStrategy defaultInstance;
  public static ResourcePropertyKeyCacheLookupStrategy defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( ResourcePropertyKeyCacheLookupStrategy.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new ResourcePropertyKeyCacheLookupStrategy();
        }
      }
    }
    return defaultInstance;
  }

  
  @Override
  public ResourceKey getResourceKey( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    if( context != null )
      return null;
    
    // propertyKeyMap is static map, it only keep the resource-key of the resourceDataProperty without context;
    return propertyKeyMap.get( resourceDataProperty );
  }

  public void registerPropertyKey( ClassProperty property, ResourceKey key )
  {
    propertyKeyMap.put( property, key );
  }
}
