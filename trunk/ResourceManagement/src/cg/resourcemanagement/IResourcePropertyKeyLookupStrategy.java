package cg.resourcemanagement;

import cg.common.property.ClassProperty;

public interface IResourcePropertyKeyLookupStrategy
{
  /*
   * get the resource key by the resource data property
   * the resource owner class maybe different from the property declaring class 
   */
  public String getResourceKey( ClassProperty resourceDataProperty, Class<?> resourceOwnerClass );
}
