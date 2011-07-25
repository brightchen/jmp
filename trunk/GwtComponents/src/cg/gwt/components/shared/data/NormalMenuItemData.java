package cg.gwt.components.shared.data;


/*
 * the MenuItemData with eventData and ResourceData
 * the NormalMenuItemData is a very generic class which can be reused by different menu.
 * so, provides IResourceClassIndicator to let the sub-class overwrite the resource class indicator
 */
public class NormalMenuItemData extends MenuItemData
{
  private static final long serialVersionUID = -8086856314308266592L;
  
  private UIEventIdentity menuEventIdentity;
  private MenuEventData eventData;
  
  public NormalMenuItemData()
  {
    this( null );
  }
  
  public NormalMenuItemData( String title )
  {
    this( title, null );
  }
  public NormalMenuItemData( UIEventIdentity menuEventIdentity, String title )
  {
    this( menuEventIdentity, title, null, (String[])null );
  }
  
  public NormalMenuItemData( String title, String commandKey )
  {
    this( title, commandKey, (String[])null );
  }
  
  public NormalMenuItemData( String title, String commandKey, String ... parameters )
  {
    this( null, title, commandKey, parameters );
  }
  
  public NormalMenuItemData( UIEventIdentity menuEventIdentity, String title, String commandKey, String ... parameters )
  {
    super( MenuItemType.NORMAL );
    setMenuEventIdentity( menuEventIdentity );
    setEventData( buildMenuEventData( commandKey, parameters ) );
    setResourceData( buildResourceData() );
    setTitle( title );
  }

  public UIEventIdentity getMenuEventIdentity()
  {
    return menuEventIdentity;
  }

  public void setMenuEventIdentity( UIEventIdentity menuEventIdentity )
  {
    this.menuEventIdentity = menuEventIdentity;
  }

  protected MenuItemResourceData buildResourceData()
  {
    return new MenuItemResourceData();
  }
  
  public String getTitle()
  {
    return getResourceData().getValue();
  }

  public void setTitle( String title )
  {
    getResourceData().setValue( title );
  }

  /*
   * template to let the sub class override this method to create more specific MenuEventData
   */
  protected MenuEventData buildMenuEventData( String commandKey, String ... parameters )
  {
    return new MenuEventData( commandKey, parameters );
  }

  public String getCommandKey()
  {
    return ( eventData == null ) ? null : eventData.getKey();
  }

  public MenuEventData getEventData()
  {
    return eventData;
  }

  public void setEventData( MenuEventData eventData )
  {
    this.eventData = eventData;
  }
}
