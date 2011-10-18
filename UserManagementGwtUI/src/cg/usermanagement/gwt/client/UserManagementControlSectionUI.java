package cg.usermanagement.gwt.client;

import java.util.List;

import cg.gwt.components.client.ui.ComponentUI;
import cg.gwt.components.client.ui.menu.NormalMenuPanel;
import cg.gwt.components.shared.data.MenuBarData;
import cg.usermanagement.gwt.client.menu.UserManagementMenuEvent;
import cg.usermanagement.gwt.shared.data.ControlSectionData;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class UserManagementControlSectionUI extends ComponentUI< ControlSectionData, HorizontalPanel >
{
  public UserManagementControlSectionUI( ControlSectionData data )
  {
    setData( data );
    setRealComponent( new HorizontalPanel() );
  }
  
  @Override
  public HorizontalPanel build()
  {
    if( getData() != null )
    {
      List< MenuBarData > menuPanelData = getData().getMenuPanelData();
      getRealComponent().add( new NormalMenuPanel( menuPanelData, new UserManagementMenuEvent() ) );
    }
    return getRealComponent();
  }
}
