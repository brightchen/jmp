package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.contentdata.shared.UIContentData;

public class PermissionData extends UIContentData< PermissionResourceData > implements Serializable
{
  private static final long serialVersionUID = -5201312829366091148L;

  private Long id;
  private long featureId;
  private String featureName;
  private long operationId;
  private String operationName;
  
  public Long getId()
  {
    return id;
  }
  public void setId( Long id )
  {
    this.id = id;
  }
  public long getFeatureId()
  {
    return featureId;
  }
  public void setFeatureId( long featureId )
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
  public long getOperationId()
  {
    return operationId;
  }
  public void setOperationId( long operationId )
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
