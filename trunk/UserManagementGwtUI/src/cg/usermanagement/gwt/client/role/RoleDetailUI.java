package cg.usermanagement.gwt.client.role;

import cg.gwt.components.client.ui.PanelCompositeUI;
import cg.usermanagement.gwt.shared.data.RoleDetailData;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RoleDetailUI extends PanelCompositeUI< RoleDetailData, VerticalPanel >
{
  private static final int ROW_SIZE = 2;    // how many rows in each line

  private RoleDetailSectionUI roleDetailSection;

  public RoleDetailUI( RoleDetailData data )
  {
    setData( data );
    roleDetailSection = new RoleDetailSectionUI( data );
    addChild( roleDetailSection );

    //permissions, use permissions ui
    
  }

  @Override
  protected void addChildComponent( Widget child, int index )
  {
    VerticalPanel container = getContainer();
    container.add( child );
  }

//  protected void getRowColumnIndex( int index, DataReference< Integer > rowIndex, DataReference< Integer > columnIndex )
//  {
//    int i1 = index / ROW_SIZE;
//    int i2 = index % ROW_SIZE;
//    rowIndex.setData( i1 );
//    columnIndex.setData( i2 );
//  }

  @Override
  protected VerticalPanel buildContainer()
  {
    return new VerticalPanel();
  }

}
