package cg.gwt.components.client.ui;

import cg.gwt.components.shared.utils.DataReference;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class UIFlexTableComposite< D > extends UIPanelComposite< D, FlexTable >
{
  public static final int DEFAULT_SIDE_SIZE = 2;
  public static enum AlignStyle
  {
    FixRowSize,
    FixColumnSize
  }
  
  private AlignStyle alignStyle;
  private int sideSize;
  
  public UIFlexTableComposite( D data )
  {
    alignStyle = AlignStyle.FixColumnSize;
    sideSize = DEFAULT_SIDE_SIZE;

    setData( data );
  }

  public void setAlignAttributes( AlignStyle alignStyle, int sideSize )
  {
    if( sideSize <= 0 )
      throw new IllegalArgumentException( "Side Size must great than 0" );
    this.alignStyle = alignStyle;
    this.sideSize = sideSize;
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
    int i1 = index / sideSize;
    int i2 = index % sideSize;
    if( AlignStyle.FixColumnSize.equals( alignStyle ) )
    {
      rowIndex.setData( i1 );
      columnIndex.setData( i2 );
    }
    else
    {
      columnIndex.setData( i1 );
      rowIndex.setData( i2 );
    }
  }
  
  protected FlexTable buildContainer()
  {
    return new FlexTable();
  }

}
