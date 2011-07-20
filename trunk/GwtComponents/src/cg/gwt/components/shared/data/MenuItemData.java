package cg.gwt.components.shared.data;

import java.io.Serializable;

/*
 * separator is a kind of menu item which don't need any data
 */
public class MenuItemData extends UIContentData implements Serializable
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
