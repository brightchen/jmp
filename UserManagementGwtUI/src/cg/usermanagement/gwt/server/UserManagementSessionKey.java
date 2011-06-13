package cg.usermanagement.gwt.server;

import cg.services.session.api.ISessionKey;

public enum UserManagementSessionKey implements ISessionKey
{
  userName,
  accountName;
  
  @Override
  public long getNumberOfKeys()
  {
    return UserManagementSessionKey.values().length;
  }

}
