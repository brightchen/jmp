package cg.resourcemanagement;

import java.lang.reflect.Method;

import cg.common.property.ClassProperty;
import cg.common.property.ClassPropertyUtil;
import cg.common.util.ReflectionUtil;
import cg.resourcemanagement.annotation.IResourceKey;

public class ResourcePropertyKeyAnnotationLookupStrategy implements IResourcePropertyKeyLookupStrategy
{
  private static ResourcePropertyKeyAnnotationLookupStrategy defaultInstance;
  
  public static ResourcePropertyKeyAnnotationLookupStrategy defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( ResourcePropertyKeyAnnotationLookupStrategy.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new ResourcePropertyKeyAnnotationLookupStrategy();
        }
      }
    }
    return defaultInstance;
  }
  
  /*
   * get the resource key via the annotation to corresponding getter/setter of resource data
   * @see cg.resourcemanagement.IResourcePropertyKeyLookupStrategy#getResourceKey(cg.common.property.ClassProperty, cg.resourcemanagement.ResourcePropertyContext)
   */
  @Override
  public ResourceKey getResourceKey( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    Method getter = ReflectionUtil.getMethod( context.getResourceDataClass(), ClassPropertyUtil.getGetterName( resourceDataProperty.getName() ), 
                                              ReflectionUtil.NO_PARAMETER );
    IResourceKey resourceKeyAnnotation = getter.getAnnotation( IResourceKey.class );
    //the IResourceKey only apply to setter or getter but not both
    if( resourceKeyAnnotation == null )
    {
      //try to get from setter;
      Method setter = ReflectionUtil.getMethod( context.getResourceDataClass(), ClassPropertyUtil.getSetterName( resourceDataProperty.getName() ), 
                                                new Class<?>[]{ Object.class } );
      resourceKeyAnnotation = setter.getAnnotation( IResourceKey.class );
    }
    
    //
    ResourceKey resourceKey = ( resourceKeyAnnotation == null ) ? null : new ResourceKey( resourceKeyAnnotation );
    return ResourceKeyUtil.mergeResourceKey( context.getSuperResourceKey(), resourceKey );
  }

}
