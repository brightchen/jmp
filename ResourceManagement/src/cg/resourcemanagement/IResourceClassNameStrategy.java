package cg.resourcemanagement;

import cg.common.property.ClassProperty;

/*
 * get the resource class name
 */
public interface IResourceClassNameStrategy
{
  public String getResourceClassName( ClassProperty resourceDataProperty, Class<?> ownerContentDataClass, Class<?> ownerResourceDataClass );
}
