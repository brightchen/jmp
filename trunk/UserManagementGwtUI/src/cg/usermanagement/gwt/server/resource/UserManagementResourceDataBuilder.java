package cg.usermanagement.gwt.server.resource;

import java.util.Locale;

import cg.usermanagement.gwt.shared.data.LoginResourceData;

public class UserManagementResourceDataBuilder
{
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
