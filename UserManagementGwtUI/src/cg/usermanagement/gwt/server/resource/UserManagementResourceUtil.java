package cg.usermanagement.gwt.server.resource;

import java.util.Locale;
import java.util.Set;

import cg.resourcemanagement.ResourceManager;
import cg.resourcemanagement.util.LocaleUtil;

public class UserManagementResourceUtil
{
  public static Set<Locale> getSupportedLocales()
  {
    return ResourceManager.getInstance().getSupportedLocales( UserManagementResourceKey.getResourceBundleBaseName() );
  }
  
  public static String getResource( UserManagementResourceKey key )
  {
    return getResource( key, getLocale() );
  }

  public static String getResource( UserManagementResourceKey key, String localeName )
  {
    return getResource( key, LocaleUtil.getLocale( localeName ) );
  }

  public static String getResource( UserManagementResourceKey key, Locale locale )
  {
    return key.getString( locale );
  }
  
  /*
   * TODO: the locale should be get from current user's session
   */
  public static Locale getLocale()
  {
    return Locale.US;
  }
  
}
