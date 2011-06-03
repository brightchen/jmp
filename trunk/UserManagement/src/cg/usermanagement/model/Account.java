package cg.usermanagement.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cg.model.api.INamedEntity;

@Entity
@Table(name="TACCOUNT")
public class Account implements INamedEntity
{
  @javax.persistence.SequenceGenerator(name="TACCOUNT_SEQ",sequenceName="TACCOUNT_SEQ")
  @javax.persistence.Id 
  @javax.persistence.GeneratedValue(strategy=javax.persistence.GenerationType.AUTO,generator="TACCOUNT_SEQ")
  @javax.persistence.Column(name="ID")
  private Long id;

  @javax.persistence.Column(name="NAME")
  private String name;

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
  public String getName()
  {
    return name;
  }
  @Override
  public void setName( String name )
  {
    this.name = name;
  }

  public String getPassword()
  {
    return password;
  }
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
  
  public boolean isExpired()
  {
    return expired;
  }
  public void setExpired( boolean expired )
  {
    this.expired = expired;
  }

  public boolean isLocked()
  {
    return locked;
  }
  public void setLocked( boolean locked )
  {
    this.locked = locked;
  }

  public AccountLockReason getLockReason()
  {
    return AccountLockReason.fromId( lockReasonId );
  }

  public void setLockReason( AccountLockReason lockReason )
  {
    lockReasonId = lockReason.getId();
  }

}
