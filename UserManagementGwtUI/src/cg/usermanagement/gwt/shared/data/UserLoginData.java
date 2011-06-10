package cg.usermanagement.gwt.shared.data;

public class UserLoginData extends LoginData
{
  private static final long serialVersionUID = 2184991758414860619L;

  public UserLoginData()
  {
    setNameTitle( "User Name" );
  }
  
  public UserLoginData( String name, String password )
  {
    this();
    setName( name );
    setPassword( password );
  }
}
