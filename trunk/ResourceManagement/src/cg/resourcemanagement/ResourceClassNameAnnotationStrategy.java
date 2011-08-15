package cg.resourcemanagement;

import cg.common.property.ClassProperty;
import cg.resourcemanagement.annotation.IResourceKey;

/*
 * get resource class name from the class annotation
 */
public class ResourceClassNameAnnotationStrategy implements IResourceClassNameStrategy
{
  public static final ResourceClassNameAnnotationStrategy defaultInstance = new ResourceClassNameAnnotationStrategy();
  
  @Override
  public String getResourceClassName( ClassProperty resourceDataProperty, ResourcePropertyContext context )
  {
    Class<?> ownerContentDataClass = context.getOwnerContentDataClass();
    IResourceKey resourceClass = ownerContentDataClass.getAnnotation( IResourceKey.class );
    if( resourceClass != null )
    {
      return resourceClass.className();
    }
    
    Class<?> ownerResourceDataClass = context.getResourceDataClass();
    resourceClass = ownerResourceDataClass.getAnnotation( IResourceKey.class );
    if( resourceClass != null )
    {
      return resourceClass.className();
    }
    return null;
  }

}
