package cg.usermanagement.gwt.server.resource;

import java.util.Locale;

import cg.resourcemanagement.ResourceManager;

public enum UserManagementResourceKey
{
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

  public String getResourceBundleName()
  {
    return "cg.usermanagement.gwt.server.resource.UserManagementResource";
  }
  
  public String getString( Locale locale )
  {
    return ResourceManager.getInstance().getString( locale, getStringKey() );
  }
}
