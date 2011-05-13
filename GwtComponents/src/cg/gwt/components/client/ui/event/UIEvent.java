package cg.gwt.components.client.ui.event;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.client.ui.IDataProvider;

/*
 * the data of the event is dynamic, it should be retrieve dynamically when fire the event.
 * so, use the IDataProvider interface
 */
public abstract class UIEvent< D > implements IDataProvider< D >
{
  private List< UIEventHandler > handlers = new ArrayList< UIEventHandler >();

  public UIEvent()
  {
  }
  
  /*
   * fire an event
   */
  public void fire()
  {
    List< UIEventHandler > theHandlers = getHandlers();
    for( UIEventHandler theHandler : theHandlers )
    {
      theHandler.handle( this );
    }
  }


  public List< UIEventHandler > getHandlers()
  {
    return handlers;
  }

  public void setHandlers( List< UIEventHandler > handlers )
  {
    this.handlers = handlers;
  }

  public void addHandler( UIEventHandler handler )
  {
    handlers.add( handler );
  }
  
}
