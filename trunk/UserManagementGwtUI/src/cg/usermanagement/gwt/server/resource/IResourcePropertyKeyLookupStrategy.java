package cg.usermanagement.gwt.server.resource;

import cg.common.property.IClassProperty;

public interface IResourcePropertyKeyLookupStrategy
{
  /*
   * get the resource key by the resource data property
   */
  public String getResourceKey( IClassProperty resourceDataProperty );
}
