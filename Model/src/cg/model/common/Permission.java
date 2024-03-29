package cg.model.common;

import cg.model.api.IEntity;



/*
 * one permission has one feature and one operation.
 * the name of the permission is <feature>_<operation>
 * the permission is not necessary to keep into the database as it can be generated by feature and operation
 */
public class Permission implements IEntity
{
  private Long id;
  private Feature feature;
  private Operation operation;

  public Permission( Feature feature, Operation operation )
  {
    setFeature( feature );
    setOperation( operation );
  }
  
  public Feature getFeature()
  {
    return feature;
  }
  public void setFeature( Feature feature )
  {
    this.feature = feature;
  }

  public Operation getOperation()
  {
    return operation;
  }
  public void setOperation( Operation operation )
  {
    this.operation = operation;
  }

  
  public String getName()
  {
    return feature.getName() + "_" + operation.getName();
  }
  

  // generate permission id according to feature/operation id
  @Override
  public Long getId()
  {
    return id;
  }
  @Override
  public void setId( Long id )
  {
    this.id = id;
  }

}
