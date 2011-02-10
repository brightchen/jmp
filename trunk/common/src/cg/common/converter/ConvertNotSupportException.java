package cg.common.converter;

public class ConvertNotSupportException extends Exception
{
  private static final long serialVersionUID = 7522555560291467765L;

  public ConvertNotSupportException()
  {
    super();
  }
  
  public ConvertNotSupportException( String message )
  {
    super( message );
  }
}
