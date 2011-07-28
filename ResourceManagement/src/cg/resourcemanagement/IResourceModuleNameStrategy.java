package cg.resourcemanagement;

import cg.common.property.ClassProperty;

public interface IResourceModuleNameStrategy
{
  public String getResourceModuleName( ClassProperty resourceDataProperty, Class<?> ownerContentDataClass, Class<?> ownerResourceDataClass );
}
