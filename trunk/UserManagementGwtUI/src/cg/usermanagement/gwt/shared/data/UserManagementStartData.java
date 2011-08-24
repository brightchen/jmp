package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cg.contentdata.shared.UIContentData;
import cg.gwt.components.shared.data.UICompositeContentData;


public class UserManagementStartData extends UICompositeContentData< UserManagementStartResourceData > implements Serializable
{
  private static final long serialVersionUID = -8520875500972991954L;
  
  private UserLoginData userLoginData;
  private AccountLoginData accountLoginData;
  private UserRegisterData userRegisterData;
  
  public UserManagementStartData(){}
  
  public UserManagementStartData( UserLoginData userLoginData, AccountLoginData accountLoginData, UserRegisterData userRegisterData )
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

  @Override
  public List< UIContentData< ? >> getSubContentDatas()
  {
    List< UIContentData< ? >> subContentDatas = new ArrayList< UIContentData< ? >>();
    if( userLoginData != null )
      subContentDatas.add( userLoginData );
    if( accountLoginData != null )
      subContentDatas.add( accountLoginData );
    if( userRegisterData != null )
      subContentDatas.add( userRegisterData );
    
    return subContentDatas;
  }
}
