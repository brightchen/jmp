package cg.usermanagement.gwt.server;

import cg.services.session.api.ISessionKey;

public enum UserManagementSessionKey implements ISessionKey
{
  locale,
  currentPageDatas,   //List< ResponseData<?> >
  
  userName,
  accountName,
  userPermissions,
  accountPermissions;
  
  @Override
  public long getNumberOfKeys()
  {
    return UserManagementSessionKey.values().length;
  }

}
