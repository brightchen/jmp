package cg.dbmanagement.gwt.shared.data;

import java.util.ArrayList;
import java.util.List;

public class QueryOutputData extends SqlOutputData
{
  private static final long serialVersionUID = -7178471939165634031L;
  
  private List< ColumnData > columnDatas;
  
  @Override
  public List< ColumnData > getOutput()
  {
    return columnDatas;
  }

  public void setColumnDatas( List< ColumnData > columnDatas )
  {
    this.columnDatas = columnDatas;
  }
  
  public void addColumnData( ColumnData columnData )
  {
    if( columnDatas == null )
      columnDatas = new ArrayList< ColumnData >();
    columnDatas.add( columnData );
  }
}
