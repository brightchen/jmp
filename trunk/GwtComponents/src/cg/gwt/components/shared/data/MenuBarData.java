package cg.gwt.components.shared.data;

import java.util.ArrayList;
import java.util.List;


//MenuBar is a collection of menuitems
//MenuBar can contain sub-MenuBar(s)
//Although the MenuBar don't have to fire a command, but we can look the open of sub-menuitem as the behavior of a command,
//so, the menubar can be looked as normal menu-item
public class MenuBarData extends NormalMenuItemData implements ICompositeContentData
{
  private static final long serialVersionUID = 718570757853564304L;

  private List< MenuItemData > menuItemDatas;
  
  public List< MenuItemData > getMenuItemDatas()
  {
    return menuItemDatas;
  }
  public void setMenuItemDatas( List< MenuItemData > menuItemDatas )
  {
    this.menuItemDatas = menuItemDatas;
  }

  public void addMenuItemData( MenuItemData menuItemData )
  {
    if( menuItemDatas == null )
      menuItemDatas = new ArrayList< MenuItemData >();
    menuItemDatas.add( menuItemData );
  }
  
  @Override
  @SuppressWarnings( "rawtypes" ) 
  public List< ? extends UIContentData > getSubContentDatas()
  {
    return menuItemDatas;
  }
}
