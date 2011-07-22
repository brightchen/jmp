package cg.usermanagement.gwt.server.resource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import cg.resourcemanagement.util.LocaleUtil;

public class UserManagementResourceDataBuilder
{
  /*
   * return the map( localeName ==> locale name resource value )
   */
  public static Map< String, String > getSupportedLocalesData()
  {
    Map< String, String > localesData = new HashMap< String, String >();
    
    Set<Locale> supportedLocales = UserManagementResourceUtil.getSupportedLocales();
    if( supportedLocales == null || supportedLocales.size() == 0 )
      return localesData;
    for( Locale locale : supportedLocales )
    {
      localesData.put( LocaleUtil.getLocaleName( locale ), UserManagementResourceUtil.getResource( UserManagementResourceKey.localename, locale ) );
    }
    return localesData;
  }


  
  /*
   * both buildUserLoginResourceData and buildAccountLoginResourceData returns same resource data type LoginResourceData
   * but they are bind to the different resources um$userlogin$* and um$accountlogin
   * it seems the instance ( instead of class ) ===> resource key map is required
   */
//  public static UserLoginResourceData buildUserLoginResourceData( Locale locale )
//  {
//    return ResourceDataManager.defaultInstance.getResourceData( locale, UserLoginResourceData.class );
//  }
//
//  public static AccountLoginResourceData buildAccountLoginResourceData( Locale locale )
//  {
//    return ResourceDataManager.defaultInstance.getResourceData( locale, AccountLoginResourceData.class );
//  }

}
