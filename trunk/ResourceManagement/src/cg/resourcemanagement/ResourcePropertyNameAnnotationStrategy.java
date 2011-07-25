package cg.resourcemanagement;

import cg.common.property.ClassProperty;

public class ResourcePropertyNameAnnotationStrategy implements IResourcePropertyNameStrategy
{
  public static final ResourcePropertyNameAnnotationStrategy defaultInstance = new ResourcePropertyNameAnnotationStrategy();
  
  @Override
  public String getResourcePropertyName( ClassProperty resourceDataProperty, Class<?> resourceOwnerClass )
  {
    return null;
  }
}
