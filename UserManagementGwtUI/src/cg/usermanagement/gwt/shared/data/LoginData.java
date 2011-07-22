package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.IValidatable;
import cg.gwt.components.shared.data.UIContentData;
import cg.gwt.components.shared.data.ValidateException;

//user or account login data
public abstract class LoginData< RD extends LoginResourceData> extends UIContentData<RD> implements IValidatable, Serializable
{
  private static final long serialVersionUID = 665267442451753317L;
  
  private LoginType loginType;
  private String name;
  private String password;

  public LoginData()
  {
  }
  
  public LoginData( String name, String password )
  {
    this();
    setName( name );
    setPassword( password );
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