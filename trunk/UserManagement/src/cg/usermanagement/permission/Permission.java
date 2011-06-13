package cg.usermanagement.permission;

public enum Permission
{
  USER_CREATE( Feature.USER, Operation.CREATE ),
  USER_READ( Feature.USER, Operation.READ ),
  
  ;
  
  private Feature feature;
  private Operation operation;
  private Permission( Feature feature, Operation operation )
  {
    this.feature = feature;
    this.operation = operation;
  }
  
  public Feature getFeature()
  {
    return feature;
  }
  
  public Operation getOperation()
  {
    return operation;
  }
}
