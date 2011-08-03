package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.ResourceData;

public class SearchUserResourceData extends ResourceData implements Serializable
{
  private static final long serialVersionUID = -1913423636479728891L;
  
  private String userName;
  private String firstName;
  private String lastName;
  private String status;
  private String phone;
  private String email;
  
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
  public String getLastName()
  {
    return lastName;
  }
  public void setLastName( String lastName )
  {
    this.lastName = lastName;
  }
  public String getStatus()
  {
    return status;
  }
  public void setStatus( String status )
  {
    this.status = status;
  }
  public String getPhone()
  {
    return phone;
  }
  public void setPhone( String phone )
  {
    this.phone = phone;
  }
  public String getEmail()
  {
    return email;
  }
  public void setEmail( String email )
  {
    this.email = email;
  }

  
}
