package cg.usermanagement.api.model;

import cg.model.api.IEntity;

// the relationship between user and account should be one to many.
// for example, one user can have one account in location A and another account in location B
// see hotmail account or gamil account 
public interface IAccount extends IEntity
{
  public String getAccountId();
  public void setAccountId( String accountId );

  public boolean isExpired();
  public void setExpired( boolean expired );
  
  public boolean isLocked();
  public void setLocked( boolean locked );

  public String getLockReason();
  public void setLockReason( String reason );
}
