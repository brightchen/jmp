package cg.usermanagement;

import cg.common.module.IModuleInfo;

public class UserManagementModuleInfo implements IModuleInfo
{
  @Override
  public int getModuleId()
  {
    return 1;
  }

  @Override
  public String getModuleName()
  {
    return "User Management Module";
  }

  @Override
  public String getModuleDescription()
  {
    return "";
  }

}
