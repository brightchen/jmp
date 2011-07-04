package cg.usermanagement.gwt.server.resource;

import java.util.Locale;

public class UserManagementResourceUtil
{
  public static String getResource( UserManagementResourceKey key )
  {
    return key.getString( getLocale() );
  }
  
  /*
   * the locale should be get from current user's session
   */
  public static Locale getLocale()
  {
    return Locale.US;
  }
}
