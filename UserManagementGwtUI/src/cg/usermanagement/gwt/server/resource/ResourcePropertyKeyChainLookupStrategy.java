package cg.usermanagement.gwt.server.resource;

import java.util.ArrayList;
import java.util.List;

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
  public String getResourceKey( IResourceDataProperty resourceDataProperty )
  {
    for( IResourcePropertyKeyLookupStrategy strategy : lookupChain )
    {
      String key = strategy.getResourceKey( resourceDataProperty );
      if( key != null )
        return key;
    }
    return null;
  }

}
