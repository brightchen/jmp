package cg.usermanagement.gwt.client;

import cg.gwt.components.client.ui.AlignedUIGroup;
import cg.gwt.components.client.ui.UIComponent;
import cg.gwt.components.client.ui.components.ButtonUI;
import cg.gwt.components.shared.data.ButtonData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelData;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class UserManagementPanelUI extends UIComponent< UserManagementPanelData, VerticalPanel >
{
  private VerticalPanel realComponent = new VerticalPanel();
  
  public UserManagementPanelUI( UserManagementPanelData data )
  {
    setData( data );
  }
  
  @Override
  public VerticalPanel build()
  {
    AlignedUIGroup< ButtonData, FlexTable > buttonGroup = new AlignedUIGroup< ButtonData, FlexTable >( getData().getButtonDatas(), new FlexTable() )
    {
      @Override
      protected Widget buildChildComponent( ButtonData childData, int index )
      {
        UserManagementButtonEvent event = new UserManagementButtonEvent();
        event.setData( UserManagementButtonMeta.toMeta( childData ) );
        ButtonUI<UserManagementButtonMeta> buttonUI = new ButtonUI<UserManagementButtonMeta>( childData );
        buttonUI.addClickEvent( event );
        return buttonUI.getRealComponent();
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
