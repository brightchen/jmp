package cg.persistence.model;

import java.io.Serializable;

public abstract class SqlOutput implements Serializable
{
  private static final long serialVersionUID = 7378927579548678003L;
  
  protected SqlOutputDesc outputDesc;

  public SqlOutputDesc getOutputDesc()
  {
    return outputDesc;
  }
  public void setOutputDesc( SqlOutputDesc outputDesc )
  {
    this.outputDesc = outputDesc;
  }

  public abstract <T> T getOutput();
}
