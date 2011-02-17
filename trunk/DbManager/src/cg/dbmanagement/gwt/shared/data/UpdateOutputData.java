package cg.dbmanagement.gwt.shared.data;

//the requirement for the serialize
//1.  it is assignable to IsSerializable  or Serializable, either because it directly implements one of these interfaces 
//    or because it derives from a superclass that does
//2. all non-final, non-transient instance fields are themselves serializable, and
//3. it has a public default (zero argument) constructor
public class UpdateOutputData extends SqlOutputData
{
  private static final long serialVersionUID = -3367138852789542306L;

  private int numOfRecords;
  
  public UpdateOutputData()
  {
    this(0);
  }
  
  public UpdateOutputData( int numOfRecords )
  {
    this.numOfRecords = numOfRecords;
    setOutputType( OutputType.UPDATE );
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
