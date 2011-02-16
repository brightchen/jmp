package cg.persistence.model;


public class UpdateOutput extends SqlOutput
{
  private static final long serialVersionUID = -2908860667252134539L;

  //number of records which affected by update
  private int numOfRecords;
  
  public UpdateOutput( int numOfRecords )
  {
    this.numOfRecords = numOfRecords;
  }
  
  @Override
  public Integer getOutput()
  {
    return numOfRecords;
  }

}
