package cg.usermanagement;

import cg.common.module.IModuleInfo;

public class UserManagementModuleInfo implements IModuleInfo
{
  private static UserManagementModuleInfo instance;
  
  public static UserManagementModuleInfo getInstance()
  {
    if( instance == null )
    {
      synchronized( UserManagementModuleInfo.class )
      {
        if( instance == null )
        {
          instance = new UserManagementModuleInfo();
        }
      }
    }
    
    return instance;
  
  }
  
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
