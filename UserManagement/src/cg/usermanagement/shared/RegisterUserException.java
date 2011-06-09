package cg.usermanagement.shared;


public class RegisterUserException extends Exception
{
  private static final long serialVersionUID = 5862851831030440808L;

  public static enum REGISTER_USER_ERROR
  {
    PASSWORD_EMTPY( "Password is empty." ),
    USERNAME_EMTPY( "User Name is emtpy." ),
    USERNAME_EXISTED( "The user name already existed." );
    
    private String reason;
    private REGISTER_USER_ERROR( String reason )
    {
      this.reason = reason;
    }
    public String getReason()
    {
      return reason;
    }
  }

  private REGISTER_USER_ERROR error;
  
  public RegisterUserException()
  {
  }

  public RegisterUserException( REGISTER_USER_ERROR error )
  {
    this.error = error;
  }
  
  public REGISTER_USER_ERROR getError()
  {
    return error;
  }
  
  public String getErrorReason()
  {
    return getError().getReason();
  }

  public void setError( REGISTER_USER_ERROR error )
  {
    this.error = error;
  }

}
