package cg.dbmanagement.gwt.shared.data;

public class DbException extends Exception
{
  private static final long serialVersionUID = -5377798502035939501L;
  
  public DbException()
  {
    
  }
  
  public DbException( String message )
  {
    super( message );
  }
}
