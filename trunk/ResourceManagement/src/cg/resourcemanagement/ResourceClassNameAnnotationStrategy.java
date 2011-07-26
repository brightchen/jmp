package cg.resourcemanagement;

import cg.common.property.ClassProperty;
import cg.gwt.components.api.IResourceDataClass;

/*
 * get resource class name from the class annotation
 */
public class ResourceClassNameAnnotationStrategy implements IResourceClassNameStrategy
{
  public static final ResourceClassNameAnnotationStrategy defaultInstance = new ResourceClassNameAnnotationStrategy();
  
  @Override
  public String getResourceClassName( ClassProperty resourceDataProperty, Class< ? > resourceOwnerClass )
  {
    IResourceDataClass resourceClass = resourceOwnerClass.getAnnotation( IResourceKey.class );
    if( resourceClass == null )
      return null;
    return resourceClass.resourceClassName();
  }

}
