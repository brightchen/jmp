package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

public class PermissionData implements Serializable
{
  private static final long serialVersionUID = -5201312829366091148L;

  private String permissionIdTitle = "Permission Id: ";
  private Long id;
  private String featureIdTitle = "Feature Id: ";
  private long featureId;
  private String featureNameTitle = "Feature Name: ";
  private String featureName;
  private String operationIdTitle = "Operation Id: ";
  private long operationId;
  private String operationNameTitle = "Operation Name: ";
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
  public String getPermissionIdTitle()
  {
    return permissionIdTitle;
  }
  public void setPermissionIdTitle( String permissionIdTitle )
  {
    this.permissionIdTitle = permissionIdTitle;
  }
  public String getFeatureIdTitle()
  {
    return featureIdTitle;
  }
  public void setFeatureIdTitle( String featureIdTitle )
  {
    this.featureIdTitle = featureIdTitle;
  }
  public String getFeatureNameTitle()
  {
    return featureNameTitle;
  }
  public void setFeatureNameTitle( String featureNameTitle )
  {
    this.featureNameTitle = featureNameTitle;
  }
  public String getOperationIdTitle()
  {
    return operationIdTitle;
  }
  public void setOperationIdTitle( String operationIdTitle )
  {
    this.operationIdTitle = operationIdTitle;
  }
  public String getOperationNameTitle()
  {
    return operationNameTitle;
  }
  public void setOperationNameTitle( String operationNameTitle )
  {
    this.operationNameTitle = operationNameTitle;
  }
}
