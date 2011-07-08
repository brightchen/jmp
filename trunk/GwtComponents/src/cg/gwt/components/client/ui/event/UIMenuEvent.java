package cg.gwt.components.client.ui.event;

import com.google.gwt.user.client.Command;

import cg.gwt.components.shared.data.MenuEventData;

public class UIMenuEvent extends UIEvent< MenuEventData > implements Command 
{
  private MenuEventData data;
  
  public UIMenuEvent(){}
  
  public UIMenuEvent( String key )
  {
    this( key, (String[])null );
  }

  public UIMenuEvent( String key, String ... parameters )
  {
    setData( new MenuEventData( key, parameters ) );
  }

  public UIMenuEvent( MenuEventData data )
  {
    setData( data );
  }
  /*
   * most menu event data are static, we know it when the menu item was creating
   * @see cg.gwt.components.client.ui.IDataProvider#getData()
   */
  @Override
  public MenuEventData getData()
  {
    return data;
  }

  @Override
  public void execute()
  {
    fire();
  }

  public void setData( MenuEventData data )
  {
    this.data = data;
  }
}
