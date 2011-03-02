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
    return build( true );
  }
  
  protected MenuBar build( MenuBarData data, boolean isRootMenuBar )
  {
    setData( data );
    return build( isRootMenuBar );
  }
  
  protected MenuBar build( boolean isRootMenuBar )
  {
    //this is the vertical menuBar
    MenuBar menuBar = new MenuBar( true );
    MenuBarData data = getData();
    if( data == null )
      return menuBar;

    List< MenuItemData > menuItemDatas = data.getMenuItemDatas();
    if( menuItemDatas == null || menuItemDatas.isEmpty() )
      return menuBar;
    for( MenuItemData miData : menuItemDatas )
    {
      appendMenuItem( menuBar, miData );
    }
    
    
    if( !isRootMenuBar )
      return menuBar;
    
    // the rootMenuBar have to add a horizontal menu bar wrapper
    {
      String title = data.getTitle();
      if( title == null )
        title = "";
      
      MenuBar containerMenuBar = new MenuBar( false );
      containerMenuBar.addItem( title, menuBar );

      return containerMenuBar;
    }
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
      MenuBarData mbData = ( MenuBarData)miData;
      MenuBarBuilder menuBarBuilder = new MenuBarBuilder();
      menuBar.addItem( mbData.getTitle(), menuBarBuilder.build( mbData, false ) );
      return;
    }
    
    //normal menuitem
    {
      NormalMenuItemBuilder builder = new NormalMenuItemBuilder();
      menuBar.addItem( builder.build( (NormalMenuItemData)miData ) );
    }
  }

}
