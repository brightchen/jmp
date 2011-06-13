package cg.usermanagement.model.view;

import java.io.Serializable;

import cg.model.api.IReadableModelView;
import cg.usermanagement.model.Permission;

public class PermissionView implements IReadableModelView< Permission >, Serializable
{
  private static final long serialVersionUID = 4963069824200751857L;

  private Permission permission;
  private long featureId;
  private String featureName;
  private long operationId;
  private String operationName;
  
  public PermissionView(){}
  
  public PermissionView( Permission permission )
  {
    setEntity( permission );
  }
  
  @Override
  public Permission getEntity()
  {
    return permission;
  }
  
  public void setEntity( Permission permission )
  {
    this.permission = permission;
    getValuesFromEntity();
  }

  @Override
  public void getValuesFromEntity()
  {
    setFeatureName( permission.getFeature().getName() );
    setFeatureId( permission.getFeature().getId() );
    setOperationName( permission.getOperation().getName() );
    setOperationId( permission.getOperation().getId() );
  }

  public String getFeatureName()
  {
    return featureName;
  }

  public void setFeatureName( String featureName )
  {
    this.featureName = featureName;
  }

  public String getOperationName()
  {
    return operationName;
  }

  public void setOperationName( String operationName )
  {
    this.operationName = operationName;
  }

  public long getFeatureId()
  {
    return featureId;
  }

  public void setFeatureId( long featureId )
  {
    this.featureId = featureId;
  }

  public long getOperationId()
  {
    return operationId;
  }

  public void setOperationId( long operationId )
  {
    this.operationId = operationId;
  }
  
  

}
