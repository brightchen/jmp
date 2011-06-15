package cg.usermanagement.permission;

import java.util.HashSet;
import java.util.Set;

import cg.common.uid.IUidData;
import cg.common.uid.UidGenerator;
import cg.model.common.IPermissionEntries;
import cg.model.common.Operation;
import cg.model.common.Permission;
import cg.usermanagement.UserManagementModuleInfo;

public enum UserManagementPermission implements IPermissionEntries, IUidData
{
  USER_CREATE( UserManagementFeature.USER, Operation.CREATE ),
  USER_READ( UserManagementFeature.USER, Operation.READ ),
  
  ;
  
  private UserManagementFeature feature;
  private Operation operation;
  private UserManagementPermission( UserManagementFeature feature, Operation operation )
  {
    this.feature = feature;
    this.operation = operation;
  }
  
  public UserManagementFeature getFeature()
  {
    return feature;
  }
  
  public Operation getOperation()
  {
    return operation;
  }
  
  /* 
   * convert it into global feature
   */
  public Permission toPermission()
  {
    Permission permission = new Permission( getFeature().toFeature(), getOperation() );
    permission.setId( UidGenerator.getDefaultInstance().getUid( this, ordinal() ) );
    return permission;
  }

  @Override
  public Set< Permission > getPermissions()
  {
    UserManagementPermission[] umps = values();
    Set< Permission > permissions = new HashSet< Permission >();
    if( umps == null || umps.length == 0 )
      return permissions;
    for( UserManagementPermission ump : umps )
    {
      Permission permission = new Permission( ump.getFeature().toFeature(), ump.getOperation() );
      permission.setId( (long)ump.ordinal() );
      permissions.add( permission );
    }
    return permissions;
  }

  @Override
  public int getModuleId()
  {
    return UserManagementModuleInfo.getInstance().getModuleId();
  }

  @Override
  public int getSectionId()
  {
    return 1;
  }

}
