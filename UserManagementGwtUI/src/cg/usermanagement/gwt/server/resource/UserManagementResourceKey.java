package cg.usermanagement.gwt.server.resource;

import java.util.Locale;

import cg.resourcemanagement.ResourceManager;

/*
 * the resource key include the information of resource data instead of visa versa.
 * As the resource data is shared by web client and web server, it is necessary to keep the data as simple as possible.
 * the resource key has format module.ui.field
 */
public enum UserManagementResourceKey
{
  localename,
  um$userlogin$title,
  um$userlogin$username,
  um$userlogin$userpassword,
  um$userlogin$login,

  um$accountlogin$title,
  um$accountlogin$accountname,
  um$accountlogin$accountpassword,
  um$accountlogin$login,

  um$userregister$title,
  um$userregister$username,
  um$userregister$userpassword,
  um$userregister$adduser,

  ;
  public String getStringKey()
  {
    return name().replace( '$', '.' );
  }

  public static String getResourceBundleBaseName()
  {
    return "cg.usermanagement.gwt.server.resource.UserManagementResource";
  }
  
  public String getString( Locale locale )
  {
    return ResourceManager.getInstance().getString( getResourceBundleBaseName(), locale, getStringKey() );
  }
}
