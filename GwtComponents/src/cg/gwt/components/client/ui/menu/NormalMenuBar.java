package cg.gwt.components.client.ui.menu;

import java.util.List;

import cg.gwt.components.shared.data.MenuBarData;
import cg.gwt.components.shared.data.MenuItemData;
import cg.gwt.components.shared.data.MenuItemType;
import cg.gwt.components.shared.data.NormalMenuItemData;

import com.google.gwt.user.client.ui.MenuBar;

public class NormalMenuBar extends MenuBar
{
  private MenuBarData menuBarData;
  
  public NormalMenuBar( MenuBarData data )
  {
    this( data, true );
  }
  
  public NormalMenuBar( MenuBarData data, boolean isRootMenuBar )
  {
    //horizontal menuBar if it is root menuber
    super( !isRootMenuBar );
    setMenuBarData( data );
    build( isRootMenuBar );
  }
  
  public MenuBarData getMenuBarData()
  {
    return menuBarData;
  }


  public void setMenuBarData( MenuBarData menuBarData )
  {
    this.menuBarData = menuBarData;
    
  }
  
  protected void build( boolean isRootMenuBar )
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
      appendMenuItem( verticalMenuBar, miData );
    }
  }
  
  protected void appendMenuItem( MenuBar menuBar, MenuItemData miData )
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
      menuBar.addItem( mbData.getTitle(), new NormalMenuBar( mbData, false ) );
      return;
    }
    
    //normal menuitem
    {
      menuBar.addItem( new NormalMenuItem( (NormalMenuItemData)miData ) );
    }
  }

}
