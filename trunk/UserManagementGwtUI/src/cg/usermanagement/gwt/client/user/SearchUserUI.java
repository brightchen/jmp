package cg.usermanagement.gwt.client.user;

import cg.gwt.components.client.ui.UIPanelComposite;
import cg.gwt.components.shared.utils.DataReference;
import cg.usermanagement.gwt.shared.data.SearchUserData;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class SearchUserUI extends UIPanelComposite< SearchUserData, FlexTable >
{
  private static final int ROW_SIZE = 2;    // how many rows in each line
  
  public SearchUserUI( SearchUserData data )
  {
    setData( data );
  }
  

  @Override
  protected void addChildComponent( Widget child, int index )
  {
    DataReference< Integer > rowIndex = new DataReference< Integer >();    
    DataReference< Integer > columnIndex = new DataReference< Integer >();
    getRowColumnIndex( index, rowIndex, columnIndex );
    
    FlexTable container = getContainer();
    container.setWidget( rowIndex.getData(), columnIndex.getData(), child );
  }

  protected void getRowColumnIndex( int index, DataReference< Integer > rowIndex, DataReference< Integer > columnIndex )
  {
    int i1 = index / ROW_SIZE;
    int i2 = index % ROW_SIZE;
    rowIndex.setData( i1 );
    columnIndex.setData( i2 );
  }

  @Override
  protected FlexTable buildContainer()
  {
    return new FlexTable();
  }
}
