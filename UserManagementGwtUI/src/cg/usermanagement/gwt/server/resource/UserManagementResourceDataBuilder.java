package cg.usermanagement.gwt.server.resource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import cg.gwt.components.shared.data.UIIdentity;
import cg.gwt.components.shared.data.UIResourceData;
import cg.resourcemanagement.util.LocaleUtil;
import cg.usermanagement.gwt.shared.data.LoginResourceData;
import cg.usermanagement.gwt.shared.data.UserManagementResourceData;

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

  public static <RD extends UserManagementResourceData> RD buildResourceData( Locale locale, Class< RD > resouceDataClass )
  {
    try
    {
      return buildResourceData( locale, resouceDataClass.newInstance() );
    }
    catch( Exception e )
    {
      
    }
    
  }

  public static <RD extends UserManagementResourceData> RD buildResourceData( Locale locale, RD resouceData )
  {
    
  }
  
  public static LoginResourceData buildUserLoginResourceData( Locale locale )
  {
    LoginResourceData data = new LoginResourceData();
    data.setName( UserManagementResourceUtil.getResource( UserManagementResourceKey.um$userloginui$username, locale ) );
    data.setPassword( UserManagementResourceUtil.getResource( UserManagementResourceKey.um$userloginui$userpassword, locale ) );
    return data;
  }

  public static LoginResourceData buildAccountLoginResourceData( Locale locale )
  {
    LoginResourceData data = new LoginResourceData();
    data.setName( UserManagementResourceUtil.getResource( UserManagementResourceKey.um$accountloginui$accountname, locale ) );
    data.setPassword( UserManagementResourceUtil.getResource( UserManagementResourceKey.um$accountloginui$accountpassword, locale ) );
    return data;
  }

}
