package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.AlignedUIGroup;
import cg.gwt.components.client.ui.ComponentUI;
import cg.gwt.components.client.ui.UIFlexTableComposite.AlignStyle;
import cg.gwt.components.client.ui.components.ButtonUI;
import cg.gwt.components.shared.data.ButtonData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelOperation;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class UserManagementPanelUI extends ComponentUI< UserManagementPanelData, VerticalPanel >
{
  public UserManagementPanelUI( UserManagementPanelData data )
  {
    setData( data );
    setRealComponent( new VerticalPanel() );
  }
  
  @Override
  public VerticalPanel build()
  {
    VerticalPanel realComponent = getRealComponent();
    if( getData() == null )
      return realComponent;
    
    AlignedUIGroup< ButtonData > buttonGroup = new AlignedUIGroup< ButtonData >( getData().getButtonDatas(), new FlexTable() )
    {
      final UserManagementPanelOperation[] operations = UserManagementPanelOperation.values();
      @Override
      protected Widget buildChildComponent( ButtonData childData, int index )
      {
        UserManagementButtonEvent event = new UserManagementButtonEvent();
        event.setData( operations[ index ] );
        ButtonUI< UserManagementPanelOperation > buttonUI = new ButtonUI< UserManagementPanelOperation >( childData );
        buttonUI.addClickEvent( event );
        return buttonUI;
      }
    };
    buttonGroup.setAlignAttributes( AlignStyle.FixColumnSize, 5 );    
    realComponent.add( buttonGroup );
    return realComponent;
  }
}
