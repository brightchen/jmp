package cg.usermanagement.gwt.client.ui;

import java.util.List;

import cg.gwt.components.client.ui.UIObjectBuilder;
import cg.usermanagement.gwt.shared.data.MenuBarData;
import cg.usermanagement.gwt.shared.data.MenuItemData;
import cg.usermanagement.gwt.shared.data.MenuItemType;
import cg.usermanagement.gwt.shared.data.NormalMenuItemData;

import com.google.gwt.user.client.ui.MenuBar;

public class MenuBarBuilder extends UIObjectBuilder< MenuBarData, MenuBar >
{
  @Override
  public MenuBar build()
  {
    MenuBar menuBar = new MenuBar();
    MenuBarData data = getData();
    if( data == null )
      return menuBar;
    if( data.getTitle() != null )
      menuBar.setTitle( data.getTitle() );
    
    List< MenuItemData > menuItemDatas = data.getMenuItemDatas();
    if( menuItemDatas == null || menuItemDatas.isEmpty() )
      return menuBar;
    for( MenuItemData miData : menuItemDatas )
    {
      appendMenuItem( menuBar, miData );
    }
    
    return menuBar;
  }

  @Override
  protected MenuBarData createData()
  {
    return new MenuBarData();
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
      MenuBarBuilder menuBarBuilder = new MenuBarBuilder();
      menuBar.addItem( "", menuBarBuilder.build( (MenuBarData)miData ) );
      return;
    }
    
    //normal menuitem
    {
      NormalMenuItemBuilder builder = new NormalMenuItemBuilder();
      menuBar.addItem( builder.build( (NormalMenuItemData)miData ) );
    }
  }

}
