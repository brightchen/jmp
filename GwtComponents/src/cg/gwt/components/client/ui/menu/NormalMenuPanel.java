package cg.gwt.components.client.ui.menu;

import java.util.List;

import cg.gwt.components.client.ui.event.UIMenuEvent;
import cg.gwt.components.shared.data.MenuBarData;

import com.google.gwt.user.client.ui.MenuBar;

/*
 * Menu Panel is menu bar which has many root menu bars. in fact, it means the root menu bar is horizontal
 * the menu event can be set when building as most menu event is static
 */
public class NormalMenuPanel extends MenuBar
{
  public NormalMenuPanel( List< MenuBarData > menuPanelData )
  {
    this( menuPanelData, null );
  }

  //all the menu item of this menu panel share the same menu event ( event type )
  public NormalMenuPanel( List< MenuBarData > menuPanelData, UIMenuEvent menuEvent )
  {
    // the menu bar is horizontal
    super( false );
    
    build( menuPanelData, menuEvent );
  }

  protected void build( List< MenuBarData > menuPanelData )
  {
    build( menuPanelData, null );
  }
  
  protected void build( List< MenuBarData > menuPanelData, UIMenuEvent menuEvent )
  {
    if( menuPanelData == null || menuPanelData.isEmpty() )
      return;
    
    for( MenuBarData menuBarData : menuPanelData )
    {
      addItem( menuBarData.getTitle(), new NormalMenuBar( menuBarData, false, menuEvent ) );
    }
  }
}
