package cg.gwt.components.client.ui.grid;

import cg.contentdata.shared.GridContentData;
import cg.contentdata.shared.TextListResourceData;
import cg.gwt.components.client.ui.ComponentUI;

import com.google.gwt.user.client.ui.Grid;

public class GridUI< T, RD extends TextListResourceData, D extends GridContentData< T, RD > > extends ComponentUI< D, Grid >
{
  public GridUI()
  {
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
    if( grid == null )
    {
      grid = new Grid();
      setRealComponent( grid );
    }
    
    D contentData = getData();
    if( contentData == null )
      return grid;
    
    int columnCount = contentData.getColumnCount();
    grid.resizeColumns( columnCount );
    int rowCount = contentData.getRowCount();
    grid.resizeRows( rowCount+1 );    //the first row is header which didn't take into account
    
//    RD resourceData = contentData.getResourceData();
    boolean buildHeader = buildHeader( grid, contentData, columnCount );
    

    //the first row is header
    int startRowIndex = buildHeader ? 1 : 0;
    for( int rowIndex = startRowIndex; rowIndex < rowCount+startRowIndex; ++rowIndex )
    {
      for( int columnIndex = 0; columnIndex < columnCount; ++columnIndex )
      {
        grid.setText( rowIndex, columnIndex, contentData.getText( rowIndex-startRowIndex, columnIndex ) );
      }
    }
    return grid;
  }

  /**
   * seems the Grid doesn't separate header and normal rows
   * @param grid
   * @param resourceData
   * @return whether buildHeader success or not, returns true if successful
   */
  public boolean buildHeader( Grid grid, D contentData, int columnCount )
  {
    if( contentData == null )
      return false;
    for( int columnIndex = 0; columnIndex < columnCount; ++columnIndex )
    {
      grid.setText( 0, columnIndex, contentData.getHeaderText( columnIndex ) );
    }
    return true;
  }

}
