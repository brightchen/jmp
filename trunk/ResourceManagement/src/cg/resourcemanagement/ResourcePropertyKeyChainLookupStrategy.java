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
  
  private static ResourcePropertyKeyChainLookupStrategy defaultInstance;
  public static ResourcePropertyKeyChainLookupStrategy defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( ResourcePropertyKeyChainLookupStrategy.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new ResourcePropertyKeyChainLookupStrategy();
        }
      }
    }
    return defaultInstance;
  }

  public ResourcePropertyKeyChainLookupStrategy()
  {
    initChain();
  }
  
  public void initChain()
  {
    lookupChain.add( ResourcePropertyKeyCacheLookupStrategy.defaultInstance() );
    lookupChain.add( ResourcePropertyKeyAnnotationLookupStrategy.defaultInstance() );
    lookupChain.add( ResourcePropertyKeyDefaultLookupStrategy.defaultInstance() );
  }

  //also need to cache the correct result to the map strategy 
  @Override
  public ResourceKey getResourceKey( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    if( ResourceKeyUtil.isValidKey( context.getSuperResourceKey()  ) )
      return context.getSuperResourceKey();
    // the resource key is merged by strategies
    ResourceKey key = null;
    for( IResourcePropertyKeyLookupStrategy strategy : lookupChain )
    {
      key = ResourceKeyUtil.mergeResourceKey( key, strategy.getResourceKey( resourceDataProperty, context ) );
      if( ResourceKeyUtil.isValidKey( key ) )
        return key;
    }
    return null;
  }

}
