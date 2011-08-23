package cg.usermanagement.gwt.client.role;

import cg.gwt.components.client.ui.PanelCompositeUI;
import cg.gwt.components.client.ui.components.ButtonUI;
import cg.usermanagement.gwt.shared.data.RoleDetailData;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RoleDetailUI extends PanelCompositeUI< RoleDetailData, VerticalPanel >
{
//  private static final int ROW_SIZE = 2;    // how many rows in each line

  private RoleDetailSectionUI roleDetailSection;
  private ButtonUI< RoleDetailData > saveRoleButton;

  public RoleDetailUI( RoleDetailData data )
  {
    setData( data );
    roleDetailSection = new RoleDetailSectionUI( data );
    addChild( roleDetailSection );

    saveRoleButton = new ButtonUI<RoleDetailData>( data.getSaveRoleButton() );
    saveRoleButton.addClickEvent( new SaveRoleEvent()
                                  {
                                    @Override
                                    public RoleDetailData getData()
                                    {
                                      RoleDetailData eventData = RoleDetailUI.this.getData();
                                      eventData.setName( roleDetailSection.getRoleName() );
                                      return eventData;
                                    }
                                  } );
    addChild( saveRoleButton );
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
