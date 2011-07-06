package cg.gwt.components.client.ui.menu;

import java.util.List;

import cg.gwt.components.shared.data.MenuBarData;

import com.google.gwt.user.client.ui.MenuBar;

/*
 * Menu Panel is menu bar which has many root menu bars. in fact, it means the root menu bar is horizontal 
 */
public class NormalMenuPanel extends MenuBar
{
  public NormalMenuPanel( List< MenuBarData > menuPanelData )
  {
    // the menu bar is horizontal
    super( false );
    
    build( menuPanelData );
  }
  
  protected void build( List< MenuBarData > menuPanelData )
  {
    if( menuPanelData == null || menuPanelData.isEmpty() )
      return;
    
    for( MenuBarData menuBarData : menuPanelData )
    {
      addItem( menuBarData.getTitle(), new NormalMenuBar( menuBarData ) );
    }
  }
}
