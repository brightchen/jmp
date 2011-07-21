package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.UIContentData;

public class UserRegisterData extends UIContentData implements Serializable
{
  private static final long serialVersionUID = -4438382721725221308L;

  // user information;
  private String userName;
  private String password;

  private String firstName;
  private String middleName;
  private String lastName;
  

  public String getUserName()
  {
    return userName;
  }

  public void setUserName( String userName )
  {
    this.userName = userName;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName( String firstName )
  {
    this.firstName = firstName;
  }

  public String getMiddleName()
  {
    return middleName;
  }

  public void setMiddleName( String middleName )
  {
    this.middleName = middleName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName( String lastName )
  {
    this.lastName = lastName;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword( String password )
  {
    this.password = password;
  }
}
