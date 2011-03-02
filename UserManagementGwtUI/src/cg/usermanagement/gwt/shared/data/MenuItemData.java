package cg.usermanagement.gwt.shared.data;

import cg.gwt.components.shared.data.IUIObjectData;

public class MenuItemData implements IUIObjectData
{
  private static final long serialVersionUID = -6887503661899518808L;
  
  public static MenuItemData SeparatorData = new MenuItemData( MenuItemType.SEPARATOR );
  
  private MenuItemType menuItemType;
  
  public MenuItemData()
  {
    this( MenuItemType.NORMAL );
  }
  
  public MenuItemData( MenuItemType menuItemType )
  {
    this.menuItemType = menuItemType;
  }

  public MenuItemType getMenuItemType()
  {
    return menuItemType;
  }

  public void setMenuItemType( MenuItemType menuItemType )
  {
    this.menuItemType = menuItemType;
  }
}
