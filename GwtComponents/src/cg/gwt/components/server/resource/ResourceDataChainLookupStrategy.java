package cg.gwt.components.server.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cg.gwt.components.shared.data.ResourceData;
import cg.gwt.components.shared.data.UIContentData;

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
  public < RD extends ResourceData > RD getResourceData( Locale locale, UIContentData contentData, Class< RD > resourceDataClass )
  {
    for( IResourceDataLookupStrategy strategy : lookupChain )
    {
      RD resourceData = strategy.getResourceData( locale, contentData, resourceDataClass );
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
