package cg.usermanagement.shared;

public class LoginException extends Exception
{
  private static final long serialVersionUID = 8831901658228075396L;

  public static enum LOGIN_ERROR
  {
    PASSWORD_EMTPY( "Password is empty" ),

    INVALID_USER( "Invalid account" ),
    USER_PASSWORD_NOT_MATCH( "The user and password does not match" ),

    INVALID_ACCOUNT( "Invalid account" ),
    ACCOUNT_PASSWORD_NOT_MATCH( "The account and password does not match" );
    
    private String reason;
    private LOGIN_ERROR( String reason )
    {
      this.reason = reason;
    }
    public String getReason()
    {
      return reason;
    }
  }
  
  private LOGIN_ERROR error;
  
  public LoginException()
  {
  }

  public LoginException( LOGIN_ERROR error )
  {
    this.error = error;
  }
  
  public LOGIN_ERROR getError()
  {
    return error;
  }
  
  public String getErrorReason()
  {
    return getError().getReason();
  }

  public void setError( LOGIN_ERROR error )
  {
    this.error = error;
  }
  
}
