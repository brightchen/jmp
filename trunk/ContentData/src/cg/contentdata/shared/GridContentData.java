package cg.contentdata.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * the data for grid. the grid data is a list of records
 * @author bchen
 *
 * @param <T> type of record data
 * @param <RD> type of resource data
 */
public abstract class GridContentData< T, RD extends ResourceData > extends UIContentData< RD > implements Serializable
{
  private static final long serialVersionUID = 3267731271944406315L;

  private List<T> recordList = new ArrayList<T>();
  
  public GridContentData(){}

  public void setRecordList( List< T > recordList )
  {
    this.recordList = recordList;
  }
  
  public void addRecord( T record )
  {
    if( record == null )
      return;
    recordList.add( record );
  }

  public int getRowCount()
  {
    return recordList.size();
  }
  
  public T getRowData( int row )
  {
    if( row < 0 || row >= getRowCount() )
    {
      throw new RuntimeException( "Row value (" + row + ") is invalid, expect between 0 and " + getRowCount() );
    }
    return recordList.get( row );
  }
  
  public String getText( int row, int column )
  {
    T rowData = getRowData( row );
    return getText( rowData, column );
  }
  
  public abstract String getText( T rowData, int column );
  public abstract int getColumnCount();
  public abstract String getHeaderText( int column );
}
