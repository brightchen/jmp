package cg.persistence.api;

public class PersistenceException extends Exception
{
  private static final long serialVersionUID = -3381444405294370310L;
  
  public PersistenceException()
  {
    super();
  }
  
  public PersistenceException( String message )
  {
    super( message );
  }
  
  public PersistenceException( String message, Throwable cause )
  {
    super( message, cause );
  }
}
