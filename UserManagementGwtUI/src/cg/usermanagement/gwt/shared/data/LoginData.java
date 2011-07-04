package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.IValidatable;
import cg.gwt.components.shared.data.ValidateException;

//user or account login data
public abstract class LoginData implements IValidatable, Serializable
{
  private static final long serialVersionUID = 665267442451753317L;
  
  private LoginType loginType;
  private String name;
  private String password;

  private LoginResourceData resourceData;
  
  public LoginData()
  {
  }
  
  public LoginData( String name, String password )
  {
    this();
    setName( name );
    setPassword( password );
  }

  public LoginResourceData getResourceData()
  {
    return resourceData;
  }

  public void setResourceData( LoginResourceData resourceData )
  {
    this.resourceData = resourceData;
  }

  public String getName()
  {
    return name;
  }
  public void setName( String name )
  {
    this.name = name;
  }
  public String getPassword()
  {
    return password;
  }
  public void setPassword( String password )
  {
    this.password = password;
  }
  
  
  public LoginType getLoginType()
  {
    return loginType;
  }

  public void setLoginType( LoginType loginType )
  {
    this.loginType = loginType;
  }

  @Override
  public void validate() throws ValidateException
  {
    if( name == null || name.isEmpty() )
      throw new ValidateException( "Name is empty" );
    if( password == null || password.isEmpty() )
      throw new ValidateException( "password is empty" );
  }
}