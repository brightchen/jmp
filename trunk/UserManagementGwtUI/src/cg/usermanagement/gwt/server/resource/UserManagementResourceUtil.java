package cg.usermanagement.gwt.server.resource;

import java.util.Locale;

import cg.resourcemanagement.util.LocaleUtil;

public class UserManagementResourceUtil
{
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
   * the locale should be get from current user's session
   */
  public static Locale getLocale()
  {
    return Locale.US;
  }
}
