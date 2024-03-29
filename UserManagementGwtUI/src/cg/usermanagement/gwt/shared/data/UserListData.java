package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.contentdata.shared.ITextList;

/**
 * this class only includes the information which need to display in the user search result for one user.
 * it's not a content data as it don't include ResourceData
 * 
 * See cg.usermanagement.gwt.shared.data.ListUsersData
 * @author bchen
 *
 */
public class UserListData implements ITextList, Serializable
{
  private static final long serialVersionUID = 3302774388266429127L;
  
  private Long id;
  private String name;
  private String firstName;
  private String lastName;
  private String status;
  private String phone;
  private String email;
  
//  private String[] texts = null;      //new String[6];      //{ name, firstName, lastName, status, phone, email };
  
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
  
  public int getCount()
  {
    return 7;   //id + texts
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

}
