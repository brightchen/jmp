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
    this( data.getTitle(), getMenuEvent( data ) );
  }

  public NormalMenuItem( String title, UIMenuEvent menuEvent )
  {
    super( title, menuEvent );
  }
  
  public static UIMenuEvent getMenuEvent( NormalMenuItemData data )
  {
    return new UIMenuEvent( data.getCommandKey() );
  }
  
  public void setMenuEvent( UIMenuEvent menuEvent )
  {
    setCommand( menuEvent );
  }

}
