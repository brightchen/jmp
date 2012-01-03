package cg.usermanagement.model.view;

import java.io.Serializable;

import cg.model.api.IReadableModelView;
import cg.usermanagement.model.User;
import cg.usermanagement.model.UserStatus;

public class UserSearchView implements IReadableModelView< User >, Serializable
{
  private static final long serialVersionUID = -8288493384417129960L;
  
  private User entity;

  public UserSearchView(){}
  
  public UserSearchView( User user )
  {
    setEntity( user );
  }
  public void setEntity( User user )
  {
    this.entity = user;
  }
  
  @Override
  public User getEntity()
  {
    return entity;
  }

  @Override
  public void getValuesFromEntity()
  {
  }

  public Long getId()
  {
    return getEntity().getId();
  }
  
  public String getName()
  {
    return getEntity().getName();
  }

  public String getFirstName()
  {
    return getEntity().getFirstName();
  }

  public String getLastName()
  {
    return getEntity().getLastName();
  }

  public String getStatus()
  {
    UserStatus status = getEntity().getStatus();
    return status == null ? "" : status.name();
  }

  public String getPhone()
  {
    return getEntity().getPhone();
  }

  public String getEmail()
  {
    return getEntity().getEmail();
  }

}
