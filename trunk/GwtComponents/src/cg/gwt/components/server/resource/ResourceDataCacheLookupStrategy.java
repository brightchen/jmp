package cg.gwt.components.server.resource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cg.gwt.components.shared.data.ResourceData;
import cg.resourcemanagement.util.LocaleUtil;

/*
 * get resource data from the cache
 */
public class ResourceDataCacheLookupStrategy implements IResourceDataLookupStrategy
{
  // the key is <resourceDataClass name> + "." + localeName
  private Map< String, ResourceData > resourceDatas = new HashMap< String, ResourceData >();
  
  @Override
  public < RD extends ResourceData > RD getResourceData( Locale locale, Class< RD > resourceDataClass )
  {
    return (RD)resourceDatas.get( getCacheKey( locale, resourceDataClass ) );
  }

  public static String getCacheKey( Locale locale, Class< ? extends ResourceData > resourceDataClass )
  {
    return resourceDataClass.getName() + "." + LocaleUtil.getLocaleName( locale );
  }
  
  public void putResourceData( Locale locale, ResourceData resourceData )
  {
    if( resourceData == null )
      return;
    resourceDatas.put( getCacheKey( locale, resourceData.getClass() ), resourceData );
  }
}
