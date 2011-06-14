package cg.usermanagement.permission;

import cg.model.common.Operation;

public enum UserManagementPermission
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
}
