package cg.persistence.model;

public enum SqlOutputType
{
  QUERY( QueryOutput.class ),
  UPDATE( UpdateOutput.class );
  
  private Class< ? extends SqlOutput > implementClass;
  
  private SqlOutputType( Class< ? extends SqlOutput > implementClass )
  {
    this.implementClass = implementClass;
  }
  
  public Class< ? extends SqlOutput > getImplementClass()
  {
    return implementClass;
  }
}
