package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.contentdata.shared.ResourceData;

public class ListUsersResourceData extends ResourceData implements Serializable
{
  private static final long serialVersionUID = -7580056450276980136L;
  
  private String text;
  private String title;
  
  private Long id;
  private String name;
  private String firstName;
  private String lastName;
  private String status;
  private String phone;
  private String email;
  public String getText()
  {
    return text;
  }
  public void setText( String text )
  {
    this.text = text;
  }
  public String getTitle()
  {
    return title;
  }
  public void setTitle( String title )
  {
    this.title = title;
  }
  public Long getId()
  {
    return id;
  }
  public void setId( Long id )
  {
    this.id = id;
  }
  public String getName()
  {
    return name;
  }
  public void setName( String name )
  {
    this.name = name;
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
