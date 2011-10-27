package cg.gwt.components.client.ui.grid;

import cg.contentdata.shared.GridContentData;
import cg.contentdata.shared.TextListResourceData;
import cg.gwt.components.client.ui.ComponentUI;

import com.google.gwt.user.client.ui.Grid;

public class GridUI< T, RD extends TextListResourceData, D extends GridContentData< T, RD > > extends ComponentUI< D, Grid >
{
  public GridUI()
  {
    Grid grid = new Grid();
    setRealComponent( grid );
  }
  
  public GridUI( D data )
  {
    this();
    setData( data );
  }
  
  @Override
  public Grid build()
  {
    Grid grid = getRealComponent();
    
    D contentData = getData();
    if( contentData == null )
      return grid;
    
    int columnCount = contentData.getColumnCount();
    RD resourceData = contentData.getResourceData();
    boolean buildHeader = buildHeader( grid, resourceData, columnCount );
    
    int rowCount = contentData.getRowCount();

    //the first row is header
    int startRowIndex = buildHeader ? 1 : 0;
    for( int rowIndex = startRowIndex; rowIndex < rowCount+startRowIndex; ++rowIndex )
    {
      for( int columnIndex = 0; columnIndex < columnCount; ++columnIndex )
      {
        grid.setText( rowIndex, columnIndex, contentData.getText( rowIndex, columnIndex ) );
      }
    }
    return null;
  }

  /**
   * seems the Grid doesn't separate header and normal rows
   * @param grid
   * @param resourceData
   * @return whether buildHeader success or not, returns true if successful
   */
  public boolean buildHeader( Grid grid, RD resourceData, int columnCount )
  {
    if( resourceData == null )
      return false;
    for( int columnIndex = 0; columnIndex < columnCount; ++columnIndex )
    {
      grid.setText( 0, columnIndex, resourceData.getText( columnIndex ) );
    }
    return true;
  }

}
