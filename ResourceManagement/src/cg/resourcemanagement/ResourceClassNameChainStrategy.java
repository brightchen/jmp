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
// the ResourceClassNameIndicatorStrategy is not a good strategy to get the resource class name as it makes the data depended on an interface which
//    is not required by the client code
//    lookupChain.add( ResourceClassNameIndicatorStrategy.defaultInstance );
    lookupChain.add( ResourceClassNameAnnotationStrategy.defaultInstance );
    lookupChain.add( ResourceClassNameDefaultStrategy.defaultInstance );
  }

  @Override
  public String getResourceClassName( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    for( IResourceClassNameStrategy strategy : lookupChain )
    {
      String value = strategy.getResourceClassName( resourceDataProperty, context );
      if( value != null )
        return value;
    }
    return null;
  }

}
