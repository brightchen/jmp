package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.IValidatable;
import cg.gwt.components.shared.data.ValidateException;

//we need user's account in order to login
public class UserLoginData implements IValidatable, Serializable
{
  private static final long serialVersionUID = 665267442451753317L;
  
  private String accountId;
  private String password;

  public UserLoginData(){}
  
  public UserLoginData( String accountId, String password )
  {
    setAccountId( accountId );
    setPassword( password );
  }
  
  public String getAccountId()
  {
    return accountId;
  }
  public void setAccountId( String accountId )
  {
    this.accountId = accountId;
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
    if( accountId == null || accountId.isEmpty() )
      throw new ValidateException( "Account Id is empty" );
    if( password == null || password.isEmpty() )
      throw new ValidateException( "password is empty" );
  }
}