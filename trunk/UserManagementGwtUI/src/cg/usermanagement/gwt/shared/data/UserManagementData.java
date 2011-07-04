package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.UIContentData;


public class UserManagementData extends UIContentData implements Serializable
{
  private static final long serialVersionUID = -8520875500972991954L;
  
  private UserLoginData userLoginData;
  private AccountLoginData accountLoginData;
  private UserRegisterData userRegisterData;
  
  public UserManagementData(){}
  
  public UserManagementData( UserLoginData userLoginData, AccountLoginData accountLoginData, UserRegisterData userRegisterData )
  {
    setUserLoginData( userLoginData );
    setAccountLoginData( accountLoginData );
    setUserRegisterData( userRegisterData );
  }
  
  public UserLoginData getUserLoginData()
  {
    return userLoginData;
  }
  public void setUserLoginData( UserLoginData userLoginData )
  {
    this.userLoginData = userLoginData;
  }
  public AccountLoginData getAccountLoginData()
  {
    return accountLoginData;
  }
  public void setAccountLoginData( AccountLoginData accountLoginData )
  {
    this.accountLoginData = accountLoginData;
  }

  public UserRegisterData getUserRegisterData()
  {
    return userRegisterData;
  }

  public void setUserRegisterData( UserRegisterData userRegisterData )
  {
    this.userRegisterData = userRegisterData;
  }

}
