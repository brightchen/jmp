package cg.resourcemanagement;

import java.util.ArrayList;
import java.util.List;

import cg.common.property.ClassProperty;

/*
 * supports ResourcePropertyKeyMapLookupStrategy and ResourcePropertyKeyDefaultLookupStrategy
 * consider other strategies, such as annotation( the ResourceData class is annotated by resource key )
 */
public class ResourcePropertyKeyChainLookupStrategy implements IResourcePropertyKeyLookupStrategy
{
  private List< IResourcePropertyKeyLookupStrategy > lookupChain = new ArrayList< IResourcePropertyKeyLookupStrategy >();
  
  public ResourcePropertyKeyChainLookupStrategy()
  {
    initChain();
  }
  
  public void initChain()
  {
    lookupChain.add( new ResourcePropertyKeyMapLookupStrategy() );
    lookupChain.add( new ResourcePropertyKeyDefaultLookupStrategy() );
  }

  //also need to cache the correct result to the map strategy 
  @Override
  public String getResourceKey( ClassProperty resourceDataProperty, Class<?> ownerContentDataClass, Class<?> ownerResourceDataClass )
  {
    for( IResourcePropertyKeyLookupStrategy strategy : lookupChain )
    {
      String key = strategy.getResourceKey( resourceDataProperty, ownerContentDataClass, ownerResourceDataClass );
      if( key != null )
        return key;
    }
    return null;
  }

}
