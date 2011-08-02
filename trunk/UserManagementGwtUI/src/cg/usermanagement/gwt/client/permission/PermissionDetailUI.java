package cg.usermanagement.gwt.client.permission;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.client.ui.AlignedUIGroup;
import cg.gwt.components.client.ui.PanelCompositeUI;
import cg.usermanagement.gwt.shared.data.PermissionDetailData;
import cg.usermanagement.gwt.shared.data.PermissionResourceData;

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
    PermissionResourceData resourceData = getData().getResourceData();
    
    List< String > alignData = new ArrayList< String >();
    alignData.add( resourceData.getPermissionId() );
    alignData.add( String.valueOf( data.getId() ) );
    alignData.add( resourceData.getFeatureId() );
    alignData.add( String.valueOf( data.getFeatureId() ) );
    alignData.add( resourceData.getFeatureName() );
    alignData.add( data.getFeatureName() );
    alignData.add( resourceData.getOperationId() );
    alignData.add( String.valueOf( data.getOperationId() ) );
    alignData.add( resourceData.getOperationName() );
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
