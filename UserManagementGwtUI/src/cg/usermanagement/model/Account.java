package cg.usermanagement.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cg.usermanagement.api.model.IAccount;
import cg.usermanagement.api.model.IAccountLockReason;

@Entity
@Table(name="ACCOUNT")
public class Account implements IAccount
{
  @javax.persistence.SequenceGenerator(name="ACCOUNT_SEQ",sequenceName="ACCOUNT_SEQ")
  @javax.persistence.Id 
  @javax.persistence.GeneratedValue(strategy=javax.persistence.GenerationType.AUTO,generator="ACCOUNT_SEQ")
  @javax.persistence.Column(name="ID")
  private Long id;

  @javax.persistence.Column(name="ACCOUNT_ID")
  private String accountId;

  @javax.persistence.Column(name="PASSWORD")
  private String password;

  @ManyToOne(fetch=FetchType.LAZY) 
  @JoinColumn(name="USER_ID") 
  private User user;
 
  
  @javax.persistence.Column(name="EXPIRED")
  private Boolean expired;

  @javax.persistence.Column(name="LOCKED")
  private Boolean locked;

  @javax.persistence.Column(name="LOCK_REASON_ID")
  private Long lockReasonId;

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

  @Override
  public String getAccountId()
  {
    return accountId;
  }
  @Override
  public void setAccountId( String accountId )
  {
    this.accountId = accountId;
  }

  @Override
  public String getPassword()
  {
    return password;
  }
  @Override
  public void setPassword( String password )
  {
    this.password = password;
  }

  public User getUser()
  {
    return user;
  }
  public void setUser( User user )
  {
    this.user = user;
  }
  
  @Override
  public boolean isExpired()
  {
    return expired;
  }
  @Override
  public void setExpired( boolean expired )
  {
    this.expired = expired;
  }

  @Override
  public boolean isLocked()
  {
    return locked;
  }
  @Override
  public void setLocked( boolean locked )
  {
    this.locked = locked;
  }

  @Override
  public IAccountLockReason getLockReason()
  {
    return AccountLockReason.fromId( lockReasonId );
  }

  @Override
  public void setLockReason( IAccountLockReason lockReason )
  {
    lockReasonId = lockReason.getId();
  }

}
