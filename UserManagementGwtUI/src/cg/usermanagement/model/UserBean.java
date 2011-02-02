package cg.usermanagement.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cg.usermanagement.api.model.IAddress;
import cg.usermanagement.api.model.IUser;


@Entity
@Table(name="TUSER")
public class UserBean implements IUser
{
  @javax.persistence.SequenceGenerator(name="TUSER_SEQ",sequenceName="TUSER_SEQ")
  @javax.persistence.Id 
  @javax.persistence.GeneratedValue(strategy=javax.persistence.GenerationType.AUTO,generator="TUSER_SEQ")
  @javax.persistence.Column(name="ID")
  private Long id;
  
  @javax.persistence.Column(name="NAME")
  private String name;

  @javax.persistence.Column(name="FIRSTNAME")
  private String firstName;

  @javax.persistence.Column(name="MIDDLENAME")
  private String middleName;

  @javax.persistence.Column(name="LASTNAME")
  private String lastName;

  @javax.persistence.Column(name="PASSWORD")
  private String password;

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

  @Override
  public void setMiddle( String middleName )
  {
    // TODO Auto-generated method stub
    
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
