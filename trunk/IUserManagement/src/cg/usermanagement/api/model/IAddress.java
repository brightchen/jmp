package cg.usermanagement.api.model;

import cg.model.api.IEntity;

public interface IAddress extends IEntity
{
  public String getAddress();
  public void setAddress( String address );
  
  public IStateProvince getStateProvince();
  public void setStateProvince( IStateProvince stateProvince );
  
  public ICountry getCountry();
  public void setCountry( ICountry country );
}
