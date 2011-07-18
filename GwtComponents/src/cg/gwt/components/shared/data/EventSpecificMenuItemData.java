package cg.gwt.components.shared.data;

import java.io.Serializable;

import cg.gwt.components.client.ui.event.UIMenuEvent;

/*
 * the NormalMenuItemData doesn't specify which menu event it will trigger
 * this class specified the menu item event
 */
public class EventSpecificMenuItemData extends NormalMenuItemData implements Serializable
{
  private static final long serialVersionUID = 4552013622908038836L;
  
  private UIMenuEvent menuEvent;

  public EventSpecificMenuItemData()
  {
    this( null );
  }
  
  public EventSpecificMenuItemData( String title )
  {
    this( title, null );
  }
  
  public EventSpecificMenuItemData( String title, UIMenuEvent menuEvent )
  {
    super( title );
    setMenuEvent( menuEvent );
  }

  public UIMenuEvent getMenuEvent()
  {
    return menuEvent;
  }

  //also set super class's menu event data to keep consistent.
  public void setMenuEvent( UIMenuEvent menuEvent )
  {
    this.menuEvent = menuEvent;
    setEventData( menuEvent.getData() );
  }

  
}
