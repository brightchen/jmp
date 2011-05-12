package cg.gwt.components.client.ui.event;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.client.ui.IDataSupport;

public abstract class UIEvent< D > implements IDataSupport< D >
{
  private D data;
  private List< UIEventHandler > handlers = new ArrayList< UIEventHandler >();

  public UIEvent( D data )
  {
    setData( data );
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

  @Override
  public void setData( D data )
  {
    this.data = data;
  }

  @Override
  public D getData()
  {
    return data;
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
