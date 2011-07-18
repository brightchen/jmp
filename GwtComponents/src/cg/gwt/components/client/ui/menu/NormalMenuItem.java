package cg.gwt.components.client.ui.menu;

import cg.gwt.components.client.ui.event.UIMenuEvent;
import cg.gwt.components.shared.data.NormalMenuItemData;

import com.google.gwt.user.client.ui.MenuItem;

public class NormalMenuItem extends MenuItem
{
  public NormalMenuItem( String title )
  {
    this( title, null );
  }
  public NormalMenuItem( NormalMenuItemData data )
  {
    this( data, null );
    setMenuEvent( buildMenuEvent( data ) );
  }
  
  /*
   * the NormalMenuItemData and UIMenuEvent are redundant data ( commandKey and parameters )
   */
  public NormalMenuItem( NormalMenuItemData data, UIMenuEvent menuEvent )
  {
    this( data.getTitle(), null );
    setMenuEvent( mergeEventData( data, menuEvent ) );
  }
  
  public NormalMenuItem( String title, UIMenuEvent menuEvent )
  {
    super( title, menuEvent );
  }
  
  public UIMenuEvent buildMenuEvent( NormalMenuItemData data )
  {
    return new UIMenuEvent( data.getEventData() );
  }
  
  public void setMenuEvent( UIMenuEvent menuEvent )
  {
    setCommand( menuEvent );
  }

  /*
   * the UIMenuEvent's MenuEventData is over NormalMenuItemData's eventData
   * the UIMenuEvent maybe a template for whole MenuBar/MenuPanel, so, the new instance of MenuEvent should be created and set data
   */
  protected UIMenuEvent mergeEventData( NormalMenuItemData data, UIMenuEvent menuEvent )
  {
    if( menuEvent.getData() != null )
      return menuEvent;
    
    UIMenuEvent newMenuEvent = menuEvent.clone();
    newMenuEvent.setData( data.getEventData() );
    return newMenuEvent;
  }
}
