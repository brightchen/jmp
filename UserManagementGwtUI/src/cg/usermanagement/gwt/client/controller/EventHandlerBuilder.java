package cg.usermanagement.gwt.client.controller;

import cg.gwt.components.client.ui.old.IUIObjectDigester;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;

public class EventHandlerBuilder implements IEventHandlerBuilder
{
  public ClickHandler buildClickHandler( final String triggerId )
  {
    return new ClickHandler()
          {
            @Override
            public void onClick( ClickEvent event )
            {
              handleEvent( triggerId, event );
            }
          };
  }

  public ClickHandler buildClickHandler( final String triggerId, final IUIObjectDigester digester )
  {
    return new ClickHandler()
          {
            @Override
            public void onClick( ClickEvent event )
            {
              handleEvent( triggerId, event, digester );
            }
          };
  }

  //
  public <E extends GwtEvent<?> > void handleEvent( String triggerId, E event )
  {
    
  }

  public <E extends GwtEvent<?> > void handleEvent( String triggerId, E event, IUIObjectDigester digester )
  {
    
  }

}
