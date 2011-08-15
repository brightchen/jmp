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
  public ResourceKey getResourceKey( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    for( IResourcePropertyKeyLookupStrategy strategy : lookupChain )
    {
      ResourceKey key = strategy.getResourceKey( resourceDataProperty, context );
      if( key != null && key.isValid() )
        return key;
    }
    return null;
  }

}
