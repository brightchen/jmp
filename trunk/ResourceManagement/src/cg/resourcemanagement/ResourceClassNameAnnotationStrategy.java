package cg.resourcemanagement;

import cg.common.property.ClassProperty;
import cg.resourcemanagement.annotation.IResourceClass;

/*
 * get resource class name from the class annotation
 */
public class ResourceClassNameAnnotationStrategy implements IResourceClassNameStrategy
{
  public static final ResourceClassNameAnnotationStrategy defaultInstance = new ResourceClassNameAnnotationStrategy();
  
  @Override
  public String getResourceClassName( ClassProperty resourceDataProperty, Class< ? > resourceOwnerClass )
  {
    IResourceClass resourceClass = resourceOwnerClass.getAnnotation( IResourceClass.class );
    if( resourceClass == null )
      return null;
    return resourceClass.resourceClassName();
  }

}
