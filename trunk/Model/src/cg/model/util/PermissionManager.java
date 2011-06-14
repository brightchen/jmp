package cg.model.util;

import java.util.HashSet;
import java.util.Set;

import cg.model.common.Permission;

public class PermissionManager
{
  private static final long serialVersionUID = 3842989715690883800L;

  private static PermissionManager instance;
  private Set< Permission > permissions = new HashSet< Permission >();
  
  private PermissionManager()
  {
  }
  
  public static PermissionManager getInstance()
  {
    if ( instance == null )
    {
      synchronized ( FeatureManager.class )
      {
        if ( instance == null )
        {
          instance = new PermissionManager();
        }
      }
    }

    return instance;
  }

  public static void addPermission( Permission permission )
  {
    getInstance().permissions.add( permission );
  }

}
