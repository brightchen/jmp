package cg.dbmanagement.gwt.shared.data;

import cg.gwt.components.shared.data.WidgetData;

public class QueryInputData implements WidgetData
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
