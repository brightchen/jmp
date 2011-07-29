package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.ResourceData;

public class UserRegisterResourceData extends ResourceData implements Serializable
{
  private static final long serialVersionUID = -658299229873795109L;

  private String title;
  private String userName;
  private String userPassword;
  private String firstName;
  private String middleName;
  private String lastName;
  private String addUser;
  
  
  public String getTitle()
  {
    return title;
  }
  public void setTitle( String title )
  {
    this.title = title;
  }
  public String getUserName()
  {
    return userName;
  }
  public void setUserName( String userName )
  {
    this.userName = userName;
  }
  public String getUserPassword()
  {
    return userPassword;
  }
  public void setUserPassword( String userPassword )
  {
    this.userPassword = userPassword;
  }
  public String getAddUser()
  {
    return addUser;
  }
  public void setAddUser( String addUser )
  {
    this.addUser = addUser;
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

}
