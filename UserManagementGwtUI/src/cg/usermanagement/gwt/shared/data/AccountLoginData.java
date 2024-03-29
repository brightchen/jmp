package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

public class AccountLoginData extends LoginData< AccountLoginResourceData > implements Serializable
{
  private static final long serialVersionUID = 5831984131192302953L;

  public AccountLoginData()
  {
    setLoginType( LoginType.ACCOUNT_LOGIN );
  }
  
  public AccountLoginData( String name, String password )
  {
    this();
    setName( name );
    setPassword( password );
  }

}
