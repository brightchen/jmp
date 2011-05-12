package cg.gwt.components.client.ui;

import java.util.List;

import cg.gwt.components.shared.utils.DataReference;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

/*
 * AlignedUIGroup is a type of UIGroup which items are aligned by column or row
 * use HTMLTable or its sub-classes as the container 
 */
public abstract class AlignedUIGroup < I, U extends FlexTable > extends UIGroup< I, U >
{
  public static enum AlignStyle
  {
    FixRowSize,
    FixColumnSize
  }
  
  private AlignStyle alignStyle = AlignStyle.FixColumnSize;
  private int sideSize = 2;
  
  public AlignedUIGroup( List<I> data, U container )
  {
    super( data, container );
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
}
