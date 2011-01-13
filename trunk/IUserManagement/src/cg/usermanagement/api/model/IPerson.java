package cg.usermanagement.api.model;

import cg.model.api.INamedEntity;

public interface IPerson extends INamedEntity
{
  public String getFirstName();
  public void setFirstName( String firstName );
  public String getMiddleName();
  public void setMiddle( String middleName );
  public String getLastName();
  public void setLastName( String lastName );
  
  public String getPassword();
  public void setPassword( String password );
  
  public IAddress getAddress();
  public void setAddress( IAddress address );
}
