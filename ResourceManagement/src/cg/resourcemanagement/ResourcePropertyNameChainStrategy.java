package cg.resourcemanagement;

import java.util.ArrayList;
import java.util.List;

import cg.common.property.ClassProperty;

public class ResourcePropertyNameChainStrategy implements IResourcePropertyNameStrategy
{
  public static final ResourcePropertyNameChainStrategy defaultInstance = new ResourcePropertyNameChainStrategy();
  
  private List< IResourcePropertyNameStrategy > lookupChain = new ArrayList< IResourcePropertyNameStrategy >();
  
  public ResourcePropertyNameChainStrategy()
  {
    initChain();
  }
  
  public void initChain()
  {
    lookupChain.add( ResourcePropertyNameDefaultStrategy.defaultInstance );
  }

  @Override
  public String getResourcePropertyName( ClassProperty resourceDataProperty, Class< ? > resourceOwnerClass )
  {
    for( IResourcePropertyNameStrategy strategy : lookupChain )
    {
      String value = strategy.getResourcePropertyName( resourceDataProperty, resourceOwnerClass );
      if( value != null )
        return value;
    }
    return null;
  }

}
