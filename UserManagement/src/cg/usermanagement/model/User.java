package cg.usermanagement.model;

import cg.usermanagement.api.model.IAddress;
import cg.usermanagement.api.model.IUser;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//
// get rid of mutual dependency: for example, use @JoinTable to join User with group,
// this way, the user depended on Group and Group depended on User
// all the dependency should be one direction instead of dual direction.
//
@SuppressWarnings( "unchecked")
@Entity
@Table( name = "U_USER")
@javax.persistence.SequenceGenerator( name = "USER_ID_SEQUENCE" , sequenceName = "USER_ID_SEQUENCE" , initialValue = 1 , allocationSize = 1)
public class User implements Serializable, IUser, Comparable
{
  private static final long serialVersionUID = -3501084345042453213L;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
//  @GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "USER_ID_SEQUENCE")
  @Column( name = "ID" )
  private Long id = null;

  @Column( name = "USER_ID" , length = 16 , nullable = false , unique = true , updatable = false)
  private String userId;

  @Column( name = "PASSWORD" , length = 32 , nullable = false )
  private String password;

  @Column( name = "FIRST_NAME" , length = 50)
  private String firstName;

  @Column( name = "MIDDLE_NAME" , length = 50)
  private String middleName;

  @Column( name = "LAST_NAME" , length = 50)
  private String lastName;

  @Column( name = "EMAIL" , length = 50)
  private String email;

  @Column( name = "PHONE_NUMBER" , length = 20)
  private String phoneNumber;


  @Column( name = "ENABLED" , nullable = false)
  private boolean enabled;

  @Column( name = "ADDR_ID" , nullable = true )
  private Long addressId;

  public User()
  {
  }

  
  public String toString()
  {
    return "User id = " + getId();
  }

  public int compareTo( Object obj )
  {
    if ( obj instanceof User )
      return this.getId().compareTo( ( (User) obj ).getId() );
    return 0;
  }


  @Override
  public String getFirstName()
  {
    return firstName;
  }

  @Override
  public void setFirstName( String firstName )
  {
    this.firstName = firstName;
  }

  @Override
  public String getMiddleName()
  {
    return middleName;
  }

  @Override
  public void setMiddle( String middleName )
  {
    this.middleName = middleName;
  }

  @Override
  public String getLastName()
  {
    return lastName;
  }

  @Override
  public void setLastName( String lastName )
  {
    this.lastName = lastName;
  }

  @Override
  public String getPassword()
  {
    return password;
  }


  @Override
  public void setPassword( String password )
  {
    this.password = password;
  }

  @Override
  public IAddress getAddress()
  {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public void setAddress( IAddress address )
  {
    // TODO Auto-generated method stub
    
  }


  @Override
  public String getName()
  {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public void setName( String name )
  {
    // TODO Auto-generated method stub
    
  }


  @Override
  public Long getId()
  {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public void setId( Long id )
  {
    // TODO Auto-generated method stub
    
  }


  @Override
  public String getUesrId()
  {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public void setUserId( String userId )
  {
    // TODO Auto-generated method stub
    
  }

}