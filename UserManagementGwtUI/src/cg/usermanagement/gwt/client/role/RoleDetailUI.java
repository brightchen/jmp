package cg.usermanagement.gwt.client.role;

import cg.gwt.components.client.ui.PanelCompositeUI;
import cg.gwt.components.shared.utils.DataReference;
import cg.usermanagement.gwt.shared.data.RoleDetailData;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class RoleDetailUI extends PanelCompositeUI< RoleDetailData, FlexTable >
{
  private static final int ROW_SIZE = 2;    // how many rows in each line

  private TextBox roleIdField;
  private TextBox roleNameField;

  public RoleDetailUI( RoleDetailData data )
  {
    setData( data );

    //id
    Label roleIdLabel = new Label( data.getResourceData().getRoleId() );
    addChild( roleIdLabel );

    roleIdField = new TextBox();
    roleIdField.setText( String.valueOf( data.getId() ) );
    roleIdField.setReadOnly( true );
    addChild( roleIdField );

    //name
    Label roleNameLabel = new Label( data.getResourceData().getRoleName() );
    addChild( roleNameLabel );

    roleNameField = new TextBox();
    roleNameField.setText( data.getName() );
    addChild( roleNameField );

    //permissions, use permissions ui
    
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
