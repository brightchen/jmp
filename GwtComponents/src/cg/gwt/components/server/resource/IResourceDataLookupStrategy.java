package cg.gwt.components.server.resource;

import java.util.Locale;

import cg.gwt.components.shared.data.ResourceData;

public interface IResourceDataLookupStrategy
{
  public < RD extends ResourceData > RD getResourceData( Locale locale, Class< RD > resourceDataClass );
}
