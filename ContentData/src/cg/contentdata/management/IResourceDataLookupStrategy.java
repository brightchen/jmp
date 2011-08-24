package cg.contentdata.management;

import java.util.Locale;

import cg.contentdata.shared.ResourceData;


public interface IResourceDataLookupStrategy
{
  public < RD extends ResourceData > RD getResourceData( Locale locale, Class< RD > resourceDataClass, ResourceDataContext context );
  
}
