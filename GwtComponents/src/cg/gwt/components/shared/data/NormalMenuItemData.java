package cg.gwt.components.shared.data;


public class NormalMenuItemData extends MenuItemData implements IUIResourceDataProvider< SimpleUIResourceData >
{
  private static final long serialVersionUID = -8086856314308266592L;
  
  private MenuEventData eventData;
  
  private SimpleUIResourceData resourceData = new SimpleUIResourceData();
  
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
    setTitle( title );
  }

  public String getTitle()
  {
    return resourceData.getValue();
  }

  public void setTitle( String title )
  {
    resourceData.setValue( title );
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

  @Override
  public SimpleUIResourceData getResourceData()
  {
    return resourceData;
  }
  
}
