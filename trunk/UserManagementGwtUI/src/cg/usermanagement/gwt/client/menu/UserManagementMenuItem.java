package cg.usermanagement.gwt.client.menu;

import cg.gwt.components.client.ui.event.UIMenuEvent;
import cg.gwt.components.client.ui.menu.NormalMenuItem;
import cg.gwt.components.shared.data.NormalMenuItemData;

public class UserManagementMenuItem extends NormalMenuItem
{

  public UserManagementMenuItem( NormalMenuItemData data )
  {
    super( data );
  }
  
  public UIMenuEvent buildMenuEvent( NormalMenuItemData data )
  {
    return new UserManagementMenuEvent( data.getEventData() );
  }


}
