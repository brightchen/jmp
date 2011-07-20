package cg.gwt.components.server.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cg.gwt.components.shared.data.ResourceData;

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
  public < RD extends ResourceData > RD getResourceData( Locale locale, Class< RD > resourceDataClass )
  {
    for( IResourceDataLookupStrategy strategy : lookupChain )
    {
      RD resourceData = strategy.getResourceData( locale, resourceDataClass );
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
