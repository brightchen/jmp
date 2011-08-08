package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.AlignedUIGroup;
import cg.gwt.components.client.ui.ComponentUI;
import cg.gwt.components.client.ui.components.ButtonUI;
import cg.gwt.components.shared.data.ButtonData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelOperation;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class UserManagementPanelUI extends ComponentUI< UserManagementPanelData, VerticalPanel >
{
  private VerticalPanel realComponent = new VerticalPanel();
  
  public UserManagementPanelUI( UserManagementPanelData data )
  {
    setData( data );
  }
  
  @Override
  public VerticalPanel build()
  {
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
    
    realComponent.add( buttonGroup );
    return realComponent;
  }

  @Override
  public VerticalPanel getRealComponent()
  {
    return realComponent;
  }

}
