package cg.service.lookup;

public class ServiceNotFoundException extends Exception
{
  private static final long serialVersionUID = 490410847462445572L;

  public ServiceNotFoundException( String message )
  {
    super( message );
  }

  public ServiceNotFoundException( String message, Exception cause )
  {
    super( message, cause );
  }

}
