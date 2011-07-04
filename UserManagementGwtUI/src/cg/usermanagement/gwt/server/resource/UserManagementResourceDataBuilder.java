package cg.usermanagement.gwt.server.resource;

import cg.usermanagement.gwt.shared.data.LoginResourceData;

public class UserManagementResourceDataBuilder
{
  public static LoginResourceData buildUserLoginResourceData()
  {
    LoginResourceData data = new LoginResourceData();
    data.setName( UserManagementResourceUtil.getResource( UserManagementResourceKey.um$userloginui$username ) );
    data.setPassword( UserManagementResourceUtil.getResource( UserManagementResourceKey.um$userloginui$userpassword ) );
    return data;
  }

  public static LoginResourceData buildAccountLoginResourceData()
  {
    LoginResourceData data = new LoginResourceData();
    data.setName( UserManagementResourceUtil.getResource( UserManagementResourceKey.um$accountloginui$accountname ) );
    data.setPassword( UserManagementResourceUtil.getResource( UserManagementResourceKey.um$accountloginui$accountpassword ) );
    return data;
  }

}
