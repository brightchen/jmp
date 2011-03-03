package cg.dbmanagement.gwt.shared.data;

import java.io.Serializable;

public class QueryInputData implements Serializable
{
  private static final long serialVersionUID = -1101696804527521868L;

  private String sql;

  public String getSql()
  {
    return sql;
  }

  public void setSql( String sql )
  {
    this.sql = sql;
  }
}
