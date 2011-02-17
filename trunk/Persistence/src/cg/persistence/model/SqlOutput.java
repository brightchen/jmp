package cg.persistence.model;

import java.io.Serializable;

public abstract class SqlOutput implements Serializable
{
  private static final long serialVersionUID = 7378927579548678003L;
  
  protected SqlOutputType outputType;

  public SqlOutputType getOutputType()
  {
    return outputType;
  }
  public void setOutputType( SqlOutputType outputType )
  {
    this.outputType = outputType;
  }

  public abstract <T> T getOutput();
}
