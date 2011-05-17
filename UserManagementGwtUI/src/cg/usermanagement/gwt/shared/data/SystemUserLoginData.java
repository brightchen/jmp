package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

//we need user's account in order to login
public class SystemUserLoginData implements Serializable
{
  private static final long serialVersionUID = 665267442451753317L;
  
  private String accountId;
  private String password;

  
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
}