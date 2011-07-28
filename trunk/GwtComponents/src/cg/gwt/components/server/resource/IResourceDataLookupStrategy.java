package cg.gwt.components.server.resource;

import java.util.Locale;

import cg.gwt.components.shared.data.ResourceData;
import cg.gwt.components.shared.data.UIContentData;

public interface IResourceDataLookupStrategy
{
  public < RD extends ResourceData > RD getResourceData( Locale locale, UIContentData contentData, Class< RD > resourceDataClass );
  
}
