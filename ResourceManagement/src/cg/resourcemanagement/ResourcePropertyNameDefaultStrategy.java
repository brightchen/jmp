package cg.resourcemanagement;

import cg.common.property.ClassProperty;

public class ResourcePropertyNameDefaultStrategy implements IResourcePropertyNameStrategy
{
  public static final ResourcePropertyNameDefaultStrategy defaultInstance = new ResourcePropertyNameDefaultStrategy();
  
  @Override
  public String getResourcePropertyName( ClassProperty resourceDataProperty, Class<?> resourceOwnerClass )
  {
    return resourceDataProperty.getName().toLowerCase();
  }

}
