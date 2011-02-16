package cg.dbmanagement.gwt.shared.data;

public class UpdateOutputData extends SqlOutputData
{
  private static final long serialVersionUID = -3367138852789542306L;

  private int numOfRecords;
  
  public UpdateOutputData( int numOfRecords )
  {
    this.numOfRecords = numOfRecords;
  }
  
  @Override
  public Integer getOutput()
  {
    return numOfRecords;
  }

  public void setOutput( int numOfRecords )
  {
    this.numOfRecords = numOfRecords;
  }
}
