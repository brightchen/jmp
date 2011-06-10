package cg.usermanagement.gwt.shared.data;

public class AccountLoginData extends LoginData
{
  public AccountLoginData()
  {
    setNameTitle( "Account Name" );
    setLoginType( LoginType.ACCOUNT_LOGIN );
  }
  
  public AccountLoginData( String name, String password )
  {
    this();
    setName( name );
    setPassword( password );
  }

}
