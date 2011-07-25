package cg.resourcemanagement;

import cg.common.property.ClassProperty;

/*
 * get resource class name from the class annotation
 */
public class ResourceClassNameAnnotationStrategy implements IResourceClassNameStrategy
{
  public static final ResourceClassNameAnnotationStrategy defaultInstance = new ResourceClassNameAnnotationStrategy();
  
  @Override
  public String getResourceClassName( ClassProperty resourceDataProperty, Class< ? > resourceOwnerClass )
  {
    return null;
  }

}
