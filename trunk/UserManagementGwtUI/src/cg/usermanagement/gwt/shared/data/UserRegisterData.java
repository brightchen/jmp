package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

public class UserRegisterData implements Serializable
{
  private static final long serialVersionUID = -4438382721725221308L;

  // user information;
  private String userName;
  private String firstName;
  private String middleName;
  private String lastName;
  
  // account information
  private String accountId;
  private String password;

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

  public String getAccountId()
  {
    return accountId;
  }

  public void setAccountId( String accountId )
  {
    this.accountId = accountId;
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
