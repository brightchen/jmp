package cg.usermanagement.gwt.server.resource;

public interface IResourcePropertyKeyLookupStrategy
{
  /*
   * get the resource key by the resource data property
   */
  public String getResourceKey( IResourceDataProperty resourceDataProperty );
}
