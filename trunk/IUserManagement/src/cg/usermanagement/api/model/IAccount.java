package cg.usermanagement.api.model;

import cg.model.api.IEntity;

// the relationship between user and account should be one to many.
// for example, one user can have one account in location A and another account in location B
// say hotmail account or gmail account 
public interface IAccount extends IEntity
{
  public String getAccountId();
  public void setAccountId( String accountId );

  public String getPassword();
  public void setPassword( String password );
  
  public boolean isExpired();
  public void setExpired( boolean expired );
  
  public boolean isLocked();
  public void setLocked( boolean locked );

  public IAccountLockReason getLockReason();
  public void setLockReason( IAccountLockReason reason );
}
