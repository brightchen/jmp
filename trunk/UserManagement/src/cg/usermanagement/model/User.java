package cg.usermanagement.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="TUSER")
public class User
{
  @javax.persistence.SequenceGenerator(name="TUSER_SEQ",sequenceName="TUSER_SEQ")
  @javax.persistence.Id 
  @javax.persistence.GeneratedValue(strategy=javax.persistence.GenerationType.AUTO,generator="TUSER_SEQ")
  @javax.persistence.Column(name="ID")
  private Long id;
  
  @javax.persistence.Column(name="NAME")
  private String name;

  @javax.persistence.Column(name="FIRST_NAME")
  private String firstName;

  @javax.persistence.Column(name="MIDDLE_NAME")
  private String middleName;

  @javax.persistence.Column(name="LAST_NAME")
  private String lastName;

  @javax.persistence.Column(name="PASSWORD")
  private String password;

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
}
