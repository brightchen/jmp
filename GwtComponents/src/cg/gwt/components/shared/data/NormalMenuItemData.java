package cg.gwt.components.shared.data;


public class NormalMenuItemData extends MenuItemData
{
  private static final long serialVersionUID = -8086856314308266592L;
  
  private String title;
  private MenuEventData eventData;
  
  public NormalMenuItemData()
  {
    this( null );
  }
  
  public NormalMenuItemData( String title )
  {
    this( title, null );
  }
  public NormalMenuItemData( String title, String commandKey )
  {
    this( title, commandKey, (String[])null );
  }
  
  public NormalMenuItemData( String title, String commandKey, String ... parameters )
  {
    super( MenuItemType.NORMAL );
    setEventData( new MenuEventData( commandKey, parameters ) );
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle( String title )
  {
    this.title = title;
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
