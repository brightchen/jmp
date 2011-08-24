package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.contentdata.shared.UIContentData;
import cg.gwt.components.shared.data.ButtonData;

public class SearchUserData extends UIContentData< SearchUserResourceData > implements Serializable
{
  private static final long serialVersionUID = 1831023655018104109L;

  private String name;
  private String firstName;
  private String lastName;
  private String status;
  private String phone;
  private String email;

  private ButtonData searchButtonData = new ButtonData( "Search User" );
  
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
    
  public ButtonData getSearchButtonData()
  {
    return searchButtonData;
  }
  
}
