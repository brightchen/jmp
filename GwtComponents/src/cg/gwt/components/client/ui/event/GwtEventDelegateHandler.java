package cg.gwt.components.client.ui.event;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/*
 * This Gwt Event Handler delegate the GwtEvent to the UIEvent
 * The Gwt event emphasis what type of action, such as click, mouse moving etc
 * The UIEvent emphasis what type of logic behavior, such as login, register etc
 */

public class GwtEventDelegateHandler< D, UE extends UIEvent< D > > implements ClickHandler
{
  private UE uiEvent;
  public GwtEventDelegateHandler( UE uiEvent )
  {
    this.uiEvent = uiEvent;
  }
  
  public void handle()
  {
    UE theUIEvent = getUIEvent();
    if( theUIEvent == null )
      return;
    theUIEvent.fire();
  }

  
  public UE getUIEvent()
  {
    return uiEvent;
  }

  public void setUIEvent( UE uiEvent )
  {
    this.uiEvent = uiEvent;
  }

  public void  onClick( ClickEvent event )
  {
    handle();
  }
  
}
