package cg.usermanagement.shared;


public class RoleException extends Exception
{
  private static final long serialVersionUID = 7603859164972807729L;
  
  public static enum ROLE_ERROR
  {
    ROLE_NAME_IS_EMPTY( "The role name is empty" ),
    ROLE_WITH_SAME_NAME_EXISTS( "There is already a role with same name" ),
    
    ;
    private String reason;
    private ROLE_ERROR( String reason )
    {
      this.reason = reason;
    }
    public String getReason()
    {
      return reason;
    }
  }

  private ROLE_ERROR error;
  
  public RoleException()
  {
  }

  public RoleException( ROLE_ERROR error )
  {
    this.error = error;
  }
  
  public ROLE_ERROR getError()
  {
    return error;
  }
  
  public String getErrorReason()
  {
    return getError().getReason();
  }

  public void setError( ROLE_ERROR error )
  {
    this.error = error;
  }
  
  @Override
  public String getMessage()
  {
    return getErrorReason();
  }
}
