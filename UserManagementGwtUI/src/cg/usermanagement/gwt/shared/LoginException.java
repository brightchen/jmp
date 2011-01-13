package cg.usermanagement.gwt.shared;

public class LoginException extends Exception
{
  private static final long serialVersionUID = 8831901658228075396L;

  public static enum LOGIN_ERROR
  {
    INVALID_USERNAME( "invalid username" ),
    USERNAME_PASSWORD_NOT_MATCH( "username and password does not match" );
    
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
