package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.contentdata.shared.TextListResourceData;

public class ListUsersGridResourceData extends TextListResourceData implements Serializable
{
  private static final long serialVersionUID = -7580056450276980136L;
  
  private String listUsersText;
  private String listUsersTitle;
  
  // following field is for column header
  private String id;
  private String name;
  private String firstName;
  private String lastName;
  private String status;
  private String phone;
  private String email;
 

  public String getListUsersText()
  {
    return listUsersText;
  }
  public void setListUsersText( String listUsersText )
  {
    this.listUsersText = listUsersText;
  }
  public String getListUsersTitle()
  {
    return listUsersTitle;
  }
  public void setListUsersTitle( String listUsersTitle )
  {
    this.listUsersTitle = listUsersTitle;
  }
  public String getId()
  {
    return id;
  }
  public void setId( String id )
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
  
  @Override
  public String getText( int index )
  {
    switch( index )
    {
      case 0:
        return String.valueOf( id );
      
      case 1:
        return getName();
        
      case 2:
        return getFirstName();
        
      case 3:
        return getLastName();
        
      case 4:
        return getStatus();
        
      case 5:
        return getPhone();
        
      case 6:
        return getEmail();
        
      default:
        throw new RuntimeException( "Index value ( " + index + " ) is invalid, expect between 0 and " + getCount() );
    }
  }
  
  @Override
  public int getCount()
  {
    return 7;
  }
}
