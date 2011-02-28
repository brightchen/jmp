package cg.usermanagement.gwt.shared.data;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.shared.data.WidgetData;

public class MenuBarData implements WidgetData
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
}
