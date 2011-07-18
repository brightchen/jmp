package cg.usermanagement.gwt.client;

import java.util.List;

import cg.gwt.components.client.ui.ComponentUI;
import cg.gwt.components.client.ui.menu.NormalMenuPanel;
import cg.gwt.components.shared.data.MenuBarData;
import cg.usermanagement.gwt.shared.data.ControlSectionData;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class UserManagementControlSectionUI extends ComponentUI< ControlSectionData, HorizontalPanel >
{
  private HorizontalPanel component = new HorizontalPanel();
  
  public UserManagementControlSectionUI( ControlSectionData data )
  {
    setData( data );
  }
  
  @Override
  public HorizontalPanel build()
  {
    List< MenuBarData > menuPanelData = getData().getMenuPanelData();
    component.add( new NormalMenuPanel( menuPanelData ) );
    return component;
  }

  @Override
  public HorizontalPanel getRealComponent()
  {
    return component;
  }
}
