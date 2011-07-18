package cg.gwt.components.client.ui.menu;

import java.util.List;

import cg.gwt.components.client.ui.event.UIMenuEvent;
import cg.gwt.components.shared.data.MenuBarData;
import cg.gwt.components.shared.data.MenuItemData;
import cg.gwt.components.shared.data.MenuItemType;
import cg.gwt.components.shared.data.NormalMenuItemData;

import com.google.gwt.user.client.ui.MenuBar;

/*
 * menu bar includes menu items, but only has one root menu item
 */
public class NormalMenuBar extends MenuBar
{
  private MenuBarData menuBarData;
  
  public NormalMenuBar( MenuBarData data )
  {
    this( data, true );
  }
  
  public NormalMenuBar( MenuBarData data, boolean isRootMenuBar )
  {
    this( data, isRootMenuBar, null );
  }
  
  //all the menu item of this menu panel share the same menu event ( event type )
  public NormalMenuBar( MenuBarData data, boolean isRootMenuBar, UIMenuEvent menuEvent )
  {
    //horizontal menuBar if it is root menuber
    super( !isRootMenuBar );
    setMenuBarData( data );
    build( isRootMenuBar, menuEvent );
  }
  
  public MenuBarData getMenuBarData()
  {
    return menuBarData;
  }


  public void setMenuBarData( MenuBarData menuBarData )
  {
    this.menuBarData = menuBarData;
    
  }
  
  protected void build( boolean isRootMenuBar, UIMenuEvent menuEvent )
  {
    MenuBarData data = getMenuBarData();
    if( data == null )
      return;

    String title = data.getTitle();
    if( title == null )
      title = "";
    
    MenuBar verticalMenuBar = this;
    if( isRootMenuBar )
    {
      //this is the vertical menuBar
      verticalMenuBar = new MenuBar( true );
      addItem( title, verticalMenuBar );
    }

    List< MenuItemData > menuItemDatas = data.getMenuItemDatas();
    if( menuItemDatas == null || menuItemDatas.isEmpty() )
      return;
    for( MenuItemData miData : menuItemDatas )
    {
      appendMenuItem( verticalMenuBar, miData, menuEvent );
    }
  }
  
  protected void appendMenuItem( MenuBar menuBar, MenuItemData miData, UIMenuEvent menuEvent )
  {
    if( MenuItemType.SEPARATOR.equals( miData.getMenuItemType() ) )
    {
      menuBar.addSeparator();
      return;
    }
    
    //menu bar
    if( miData instanceof MenuBarData )
    {
      MenuBarData mbData = ( MenuBarData)miData;
      //this normal menu bar is NOT root
      menuBar.addItem( mbData.getTitle(), new NormalMenuBar( mbData, false ) );
      return;
    }
    
    //normal menuitem
    {
      menuBar.addItem( new NormalMenuItem( (NormalMenuItemData)miData, menuEvent ) );
    }
  }

}
