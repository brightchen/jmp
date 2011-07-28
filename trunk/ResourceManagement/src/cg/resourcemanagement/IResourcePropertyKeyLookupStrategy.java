package cg.resourcemanagement;

import cg.common.property.ClassProperty;

public interface IResourcePropertyKeyLookupStrategy
{
  /*
   * get the resource key by the resource data property
   * the resource owner class maybe different from the property declaring class 
   *
   * parameters
   * resourceDataProperty - the property of the resource data, one property corresponding to one field/getter/setter
   * ownerContentDataClass: the content data class which owns the resource data class of this property
   * ownerResourceDataClass: the resource data class which owns this property
   */
  public String getResourceKey( ClassProperty resourceDataProperty, Class<?> ownerContentDataClass, Class<?> ownerResourceDataClass );
}
