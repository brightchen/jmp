package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.ResourceData;

public class PermissionResourceData extends ResourceData implements Serializable
{
  private static final long serialVersionUID = 4996625575111392785L;

  private String permissionId;
  private String featureId;
  private String featureName;
  private String operationId;
  private String operationName;
  
  
  public String getPermissionId()
  {
    return permissionId;
  }
  public void setPermissionId( String permissionId )
  {
    this.permissionId = permissionId;
  }
  public String getFeatureId()
  {
    return featureId;
  }
  public void setFeatureId( String featureId )
  {
    this.featureId = featureId;
  }
  public String getFeatureName()
  {
    return featureName;
  }
  public void setFeatureName( String featureName )
  {
    this.featureName = featureName;
  }
  public String getOperationId()
  {
    return operationId;
  }
  public void setOperationId( String operationId )
  {
    this.operationId = operationId;
  }
  public String getOperationName()
  {
    return operationName;
  }
  public void setOperationName( String operationName )
  {
    this.operationName = operationName;
  }

  
}
