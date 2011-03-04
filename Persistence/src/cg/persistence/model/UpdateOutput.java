package cg.persistence.model;


public class UpdateOutput extends SqlOutput
{
  private static final long serialVersionUID = -2908860667252134539L;

  //number of records which affected by update
  private int numOfRecords;

  public UpdateOutput()
  {
    this( 0 );
  }
  
  public UpdateOutput( int numOfRecords )
  {
    setOutputType( SqlOutputType.UPDATE );
    this.numOfRecords = numOfRecords;
  }
  
  public void setNumOfRecords( int numOfRecords )
  {
    this.numOfRecords = numOfRecords;
  }
  
  @SuppressWarnings( "unchecked" )
  @Override
  public Integer getOutput()
  {
    return numOfRecords;
  }

}
