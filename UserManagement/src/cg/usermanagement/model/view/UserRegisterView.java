package cg.usermanagement.model.view;

import java.io.Serializable;

import cg.model.api.IModleUpdatableView;
import cg.usermanagement.model.Account;
import cg.usermanagement.model.User;

public class UserRegisterView implements IModleUpdatableView< User >, Serializable
{
  private static final long serialVersionUID = -2365141091863629354L;

  private User entity;
  private Account account;
  
  public UserRegisterView(){}
  
  public UserRegisterView( User entity, Account account )
  {
    setEntity( entity );
    setAccount( account );
  }
  
  @Override
  public User getEntity()
  {
    if( entity == null )
      entity = new User();
    return entity;
  }
  
  public Account getAccount()
  {
    if( account == null )
      account = new Account();
    return account;
  }

  @Override
  public void getValuesFromEntity()
  {
  }

  @Override
  public void setValuesToEntity()
  {
    // create the relationship between user and account;
    getAccount().setUser( getEntity() );
  }

  
  public void setEntity( User entity )
  {
    this.entity = entity;
  }

  public void setAccount( Account account )
  {
    this.account = account;
  }

  public String getUserName()
  {
    return getEntity().getName();
  }

  public void setUserName( String userName )
  {
    getEntity().setName( userName );
  }

  public String getFirstName()
  {
    return getEntity().getFirstName();
  }

  public void setFirstName( String firstName )
  {
    getEntity().setFirstName( firstName );
  }

  public String getMiddleName()
  {
    return getEntity().getMiddleName();
  }

  public void setMiddleName( String middleName )
  {
    getEntity().setMiddle( middleName );
  }

  public String getLastName()
  {
    return getEntity().getLastName();
  }

  public void setLastName( String lastName )
  {
    getEntity().setLastName( lastName );
  }

  public String getName()
  {
    return getAccount().getName();
  }

  public void setName( String accountId )
  {
    getAccount().setName( accountId );
  }

  public String getPassword()
  {
    return getAccount().getPassword();
  }

  public void setPassword( String password )
  {
    getAccount().setPassword( password );
  }


}
