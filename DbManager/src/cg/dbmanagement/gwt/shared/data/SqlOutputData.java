package cg.dbmanagement.gwt.shared.data;

import java.io.Serializable;

//the object SqlOutput can not be used by the GWT frontend.
//transform SqlOutput to this class
//this class should be simple and short. so, don't considerate flexibility too much 
public abstract class SqlOutputData implements Serializable
{
  private static final long serialVersionUID = -2826602840973139864L;
  
  public static enum OUTPUT_TYPE
  {
    UPDATE_OUTPUT,
    QUERY_OUTPUT
  }

  private OUTPUT_TYPE outputType;
  
  public abstract <T> T getOutput();

  public OUTPUT_TYPE getOutputType()
  {
    return outputType;
  }
  public void setOutputType( OUTPUT_TYPE outputType )
  {
    this.outputType = outputType;
  }
}
