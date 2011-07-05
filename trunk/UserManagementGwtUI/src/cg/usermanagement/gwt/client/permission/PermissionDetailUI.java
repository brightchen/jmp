package cg.usermanagement.gwt.client.permission;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.client.ui.AlignedUIGroup;
import cg.gwt.components.client.ui.PanelCompositeUI;
import cg.usermanagement.gwt.shared.data.PermissionDetailData;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PermissionDetailUI extends PanelCompositeUI< PermissionDetailData, Panel >
{
  public PermissionDetailUI( PermissionDetailData data )
  {
    setData( data );
    
    List< String > alignData = new ArrayList< String >();
    alignData.add( data.getPermissionIdTitle() );
    alignData.add( String.valueOf( data.getId() ) );
    alignData.add( data.getFeatureIdTitle() );
    alignData.add( String.valueOf( data.getFeatureId() ) );
    alignData.add( data.getFeatureNameTitle() );
    alignData.add( data.getFeatureName() );
    alignData.add( data.getOperationIdTitle() );
    alignData.add( String.valueOf( data.getOperationId() ) );
    alignData.add( data.getOperationNameTitle() );
    alignData.add( data.getOperationName() );
    
    AlignedUIGroup< String > permissionUI = new AlignedUIGroup< String >( alignData )
    {
      @Override
      public Widget buildChildComponent( String childData, int index )
      {
        if( index % 2 == 0 )
        {
          return new Label( childData );
        }
        
        {
          TextBox tb = new TextBox();
          tb.setText( childData );
          return tb;
        }
      }
    };
    
    addChild( permissionUI );

  }
  
  @Override
  protected Panel buildContainer()
  {
    return new VerticalPanel();
  }

}
