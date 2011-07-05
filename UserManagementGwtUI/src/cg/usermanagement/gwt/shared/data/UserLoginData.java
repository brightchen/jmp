package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

public class UserLoginData extends LoginData implements Serializable
{
  private static final long serialVersionUID = 2184991758414860619L;

  public UserLoginData()
  {
    setLoginType( LoginType.USER_LOGIN );
  }
  
  public UserLoginData( String name, String password )
  {
    this();
    setName( name );
    setPassword( password );
  }
}
