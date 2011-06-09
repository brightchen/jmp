package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.IValidatable;
import cg.gwt.components.shared.data.ValidateException;

//we need user's account in order to login
public class UserLoginData implements IValidatable, Serializable
{
  private static final long serialVersionUID = 665267442451753317L;
  
  private String userName;
  private String password;

  public UserLoginData(){}
  
  public UserLoginData( String userName, String password )
  {
    setUserName( userName );
    setPassword( password );
  }
  
  public String getUserName()
  {
    return userName;
  }
  public void setUserName( String userName )
  {
    this.userName = userName;
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
  public void validate() throws ValidateException
  {
    if( userName == null || userName.isEmpty() )
      throw new ValidateException( "Account Id is empty" );
    if( password == null || password.isEmpty() )
      throw new ValidateException( "password is empty" );
  }
}