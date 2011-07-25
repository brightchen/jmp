package cg.resourcemanagement;

import java.util.ArrayList;
import java.util.List;

import cg.common.property.ClassProperty;

public class ResourceClassNameChainStrategy implements IResourceClassNameStrategy
{
  public static final ResourceClassNameChainStrategy defaultInstance = new ResourceClassNameChainStrategy();
  
  private List< IResourceClassNameStrategy > lookupChain = new ArrayList< IResourceClassNameStrategy >();
  
  public ResourceClassNameChainStrategy()
  {
    initChain();
  }
  
  public void initChain()
  {
    lookupChain.add( ResourceClassNameAnnotationStrategy.defaultInstance );
    lookupChain.add( ResourceClassNameDefaultStrategy.defaultInstance );
  }

  @Override
  public String getResourceClassName( ClassProperty resourceDataProperty, Class< ? > resourceOwnerClass )
  {
    for( IResourceClassNameStrategy strategy : lookupChain )
    {
      String value = strategy.getResourceClassName( resourceDataProperty, resourceOwnerClass );
      if( value != null )
        return value;
    }
    return null;
  }

}
