package cg.usermanagement.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cg.model.api.INamedEntity;

/*
 * A user can have 0..n accounts, the user's permission is the union of its accounts' permission
 * An actor can login as a User or as an account
 */
@Entity
@Table(name="TUSER")
public class User implements INamedEntity
{
  @javax.persistence.SequenceGenerator(name="TUSER_SEQ",sequenceName="TUSER_SEQ")
  @javax.persistence.Id 
  @javax.persistence.GeneratedValue(strategy=javax.persistence.GenerationType.AUTO,generator="TUSER_SEQ")
  @javax.persistence.Column(name="ID")
  private Long id;
  
  @javax.persistence.Column( name="NAME", length = 50, nullable = false, unique = true, updatable = false )
  private String name;

  @javax.persistence.Column( name="PASSWORD", length = 50 )
  private String password;

  @javax.persistence.Column( name="FIRST_NAME", length = 50 )
  private String firstName;

  @javax.persistence.Column( name="MIDDLE_NAME", length = 50 )
  private String middleName;

  @javax.persistence.Column( name="LAST_NAME", length = 50 )
  private String lastName;

  @javax.persistence.Column( name="EMAIL", length = 50 )
  private String email;

  @javax.persistence.Column( name="PHONE", length = 20 )
  private String phone;

  @Override
  public Long getId()
  {
    return id;
  }
  @Override
  public void setId( Long id )
  {
    this.id = id;
  }

  @Override
  public String getName()
  {
    return name;
  }
  @Override
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

  public void setMiddle( String middleName )
  {
    this.middleName = middleName;
  }
  public String getEmail()
  {
    return email;
  }
  public void setEmail( String email )
  {
    this.email = email;
  }
  public String getPhone()
  {
    return phone;
  }
  public void setPhone( String phone )
  {
    this.phone = phone;
  }
  
  
}
