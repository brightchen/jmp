package cg.usermanagement.gwt.shared.data;

public class UserLoginData extends LoginData
{
  private static final long serialVersionUID = 2184991758414860619L;

  public UserLoginData()
  {
    setNameTitle( System.getProperty( "userName", "User Name" ) );
    setLoginType( LoginType.USER_LOGIN );
  }
  
  public UserLoginData( String name, String password )
  {
    this();
    setName( name );
    setPassword( password );
  }
}
