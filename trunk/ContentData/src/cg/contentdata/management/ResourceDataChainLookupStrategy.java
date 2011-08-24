package cg.contentdata.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cg.contentdata.shared.ResourceData;

public class ResourceDataChainLookupStrategy implements IResourceDataLookupStrategy
{
  private List< IResourceDataLookupStrategy > lookupChain = new ArrayList< IResourceDataLookupStrategy >();
  private ResourceDataCacheLookupStrategy cache;
  
  public ResourceDataChainLookupStrategy()
  {
    initChain();
  }
  
  public void initChain()
  {
    cache = new ResourceDataCacheLookupStrategy();
    lookupChain.add( cache );
    lookupChain.add( new ResourceDataBuilder() );
  }

  @Override
  public < RD extends ResourceData > RD getResourceData( Locale locale, Class< RD > resourceDataClass, ResourceDataContext context )
  {
    for( IResourceDataLookupStrategy strategy : lookupChain )
    {
      RD resourceData = strategy.getResourceData( locale, resourceDataClass, context );
      if( resourceData != null )
      {
        if( !cache.equals( strategy ) )
          cache.putResourceData( locale, resourceData );
        return resourceData;
      }
    }
    return null;
  }

}
