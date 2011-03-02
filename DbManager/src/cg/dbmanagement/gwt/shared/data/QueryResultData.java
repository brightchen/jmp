package cg.dbmanagement.gwt.shared.data;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.shared.data.IUIObjectData;

public class QueryResultData implements IUIObjectData
{
  private static final long serialVersionUID = -5604057943654882297L;

  //the first array in the list is the header, all others are data
  private List< String[] > headerData;
  private int columns;
  
  //header should not be null;
  public void setHeader( String[] header )
  {
    columns = header.length;
    if( headerData == null )
      headerData = new ArrayList< String[] >();
    headerData.set( 0, header );
  }

  public String[] getHeader()
  {
    if( headerData == null || headerData.size() == 0 )
      return null;
    return headerData.get( 0 );
  }
  
  public int getColumns()
  {
    return columns;
  }
  
  public void addRow( String[] row )
  {
    if( row == null )
      return;
    if( headerData == null )
      throw new RuntimeException( "The header should be add first" );
    if( columns == 0 )
      throw new RuntimeException( "The header should be add first" );
    if( row.length != columns )
      throw new RuntimeException( "The header should be add first" );
    headerData.add( row );
  }
}
