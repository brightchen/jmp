package cg.resourcemanagement;

import java.util.ArrayList;
import java.util.List;

import cg.common.property.ClassProperty;

public class ResourceModuleNameChainStrategy implements IResourceModuleNameStrategy
{
  public static final ResourceModuleNameChainStrategy defaultInstance = new ResourceModuleNameChainStrategy();
  
  private List< IResourceModuleNameStrategy > lookupChain = new ArrayList< IResourceModuleNameStrategy >();
  
  public ResourceModuleNameChainStrategy()
  {
    initChain();
  }
  
  public void initChain()
  {
    lookupChain.add( ResourceModuleNameMapStrategy.defaultInstance );
  }

  @Override
  public String getResourceModuleName( ClassProperty resourceDataProperty, Class< ? > resourceOwnerClass )
  {
    for( IResourceModuleNameStrategy strategy : lookupChain )
    {
      String value = strategy.getResourceModuleName( resourceDataProperty, resourceOwnerClass );
      if( value != null )
        return value;
    }
    return null;
  }

}
