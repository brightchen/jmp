package cg.usermanagement.model.view;

import java.io.Serializable;

import cg.model.api.IModleUpdatableView;
import cg.usermanagement.model.User;

public class UserRegisterView implements IModleUpdatableView< User >, Serializable
{
  private static final long serialVersionUID = -2365141091863629354L;

  private User entity;
  
  public UserRegisterView(){}
  
  public UserRegisterView( User entity )
  {
    setEntity( entity );
  }
  
  @Override
  public User getEntity()
  {
    if( entity == null )
      entity = new User();
    return entity;
  }
  
  @Override
  public void getValuesFromEntity()
  {
  }

  @Override
  public void setValuesToEntity()
  {
  }

  
  public void setEntity( User entity )
  {
    this.entity = entity;
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
    return getEntity().getName();
  }

  public void setName( String accountId )
  {
    getEntity().setName( accountId );
  }

  public String getPassword()
  {
    return getEntity().getPassword();
  }

  public void setPassword( String password )
  {
    getEntity().setPassword( password );
  }


}
