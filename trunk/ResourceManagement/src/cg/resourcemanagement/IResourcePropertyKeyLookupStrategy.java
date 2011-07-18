package cg.resourcemanagement;

import cg.common.property.ClassProperty;

public interface IResourcePropertyKeyLookupStrategy
{
  /*
   * get the resource key by the resource data property
   */
  public String getResourceKey( ClassProperty resourceDataProperty );
}
