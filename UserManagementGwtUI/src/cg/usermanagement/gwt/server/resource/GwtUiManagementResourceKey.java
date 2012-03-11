package cg.gwtui.server.resource;

import java.util.Locale;

import cg.resourcemanagement.ResourceManager;

/*
 * the resource key include the information of resource data instead of visa versa.
 * As the resource data is shared by web client and web server, it is necessary to keep the data as simple as possible.
 * the resource key has format module.ui.field
 */
public enum GwtUiManagementResourceKey
{
  localename,
  um$userlogin$title,
  um$userlogin$name,
  um$userlogin$password,
  um$userlogin$login,

  um$accountlogin$title,
  um$accountlogin$name,
  um$accountlogin$password,
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
    // the property file suppose to locate under same directory with the name like this class but don't have key;
//    String className = GwtUiManagementResourceKey.class.getClass().getName();
//    return className.substring( 0, className.length() - "Key".length() );
    return "cg.gwtui.server.resource.GwtUiManagementResource";
  }
  
  public String getString( Locale locale )
  {
    return ResourceManager.getInstance().getString( getResourceBundleBaseName(), locale, getStringKey() );
  }
}
