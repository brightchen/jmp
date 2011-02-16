package cg.persistence.model;

public class SqlOutputDesc
{
  public static enum OUTPUT_TYPE
  {
    UPDATE_OUTPUT,
    QUERY_OUTPUT
  }
  
  private OUTPUT_TYPE outputType;

  public OUTPUT_TYPE getOutputType()
  {
    return outputType;
  }

  public void setOutputType( OUTPUT_TYPE outputType )
  {
    this.outputType = outputType;
  }
}
