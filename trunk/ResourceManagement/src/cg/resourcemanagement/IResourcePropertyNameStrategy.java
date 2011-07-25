package cg.resourcemanagement;

import cg.common.property.ClassProperty;

public interface IResourcePropertyNameStrategy
{
  public String getResourcePropertyName( ClassProperty resourceDataProperty, Class<?> resourceOwnerClass );
}
