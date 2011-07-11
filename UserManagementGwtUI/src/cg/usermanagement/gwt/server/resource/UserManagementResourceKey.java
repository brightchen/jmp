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
  um$userloginui$title,
  um$userloginui$username,
  um$userloginui$userpassword,
  um$userloginui$login,

  um$accountloginui$title,
  um$accountloginui$accountname,
  um$accountloginui$accountpassword,
  um$accountloginui$login,

  um$userregisterui$title,
  um$userregisterui$username,
  um$userregisterui$userpassword,
  um$userregisterui$adduser,

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
