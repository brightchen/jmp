package cg.usermanagement.gwt.server.resource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import cg.common.property.ClassProperty;
import cg.common.property.ClassPropertyUtil;
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
      return null;
    }
    
  }

  /*
   * the ResourceData should only include the information of resource data, we can get all the properties of the resource data,
   * and then get the resource key from resource data class properties, and then get the resource value from resource key
   * 
   * ResourceData class ==> resource data class properties ==> resource keys ==> resource values
   */
  public static <RD extends UserManagementResourceData> RD buildResourceData( Locale locale, RD resourceData )
  {
    Class< RD > resourceDataClass = (Class< RD >)resourceData.getClass();
    Set< ClassProperty > classProperties = ClassPropertyUtil.getClassProperties( resourceDataClass, UserManagementResourceData.class );
    for( ClassProperty classProperty : classProperties )
    {
      String resourceValue = UserManagementResourceDataManager.defaultInstance.getResourceKey( classProperty );
      UserManagementResourceDataManager.setResourceValue( resourceData, classProperty, resourceValue );
    }
    return resourceData;
  }
  
  public static LoginResourceData buildUserLoginResourceData( Locale locale )
  {
    LoginResourceData data = new LoginResourceData();
    data.setName( UserManagementResourceUtil.getResource( UserManagementResourceKey.um$userlogin$username, locale ) );
    data.setPassword( UserManagementResourceUtil.getResource( UserManagementResourceKey.um$userlogin$userpassword, locale ) );
    return data;
  }

  public static LoginResourceData buildAccountLoginResourceData( Locale locale )
  {
    LoginResourceData data = new LoginResourceData();
    data.setName( UserManagementResourceUtil.getResource( UserManagementResourceKey.um$accountlogin$accountname, locale ) );
    data.setPassword( UserManagementResourceUtil.getResource( UserManagementResourceKey.um$accountlogin$accountpassword, locale ) );
    return data;
  }

}
