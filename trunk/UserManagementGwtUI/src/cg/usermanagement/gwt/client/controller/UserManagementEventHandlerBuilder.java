package cg.usermanagement.gwt.client.controller;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;

public class UserManagementEventHandlerBuilder
{
  public ClickHandler buildLoginHandler()
  {
    return new ClickHandler()
    {
      @Override
      public void onClick( ClickEvent event )
      {
        handleLoginEvent( event );
      }
    };
  }
  
  public void handleLoginEvent( GwtEvent<?> event )
  {
    
  }
  
  public void handleLogin( String accountId, String password )
  {
    
  }
}
