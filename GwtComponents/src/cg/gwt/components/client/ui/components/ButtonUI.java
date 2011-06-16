package cg.gwt.components.client.ui.components;

import cg.gwt.components.client.ui.UIComponent;
import cg.gwt.components.client.ui.event.GwtEventDelegateHandler;
import cg.gwt.components.client.ui.event.UIEvent;
import cg.gwt.components.shared.data.ButtonData;

import com.google.gwt.user.client.ui.Button;

/*
 * ED - Event Data type
 */
public class ButtonUI< ED > extends UIComponent< ButtonData, Button >
{
  private Button button;
  private UIEvent<ED> clickEvent;
  
  public ButtonUI( ButtonData data )
  {
    setData( data );
  }
  
  @Override
  public Button build()
  {
    ButtonData data = getData();
    button = new Button();
    button.setText( data.getTitle() );
    button.setEnabled( data.isEnabled() );

    if( clickEvent != null )
      button.addClickHandler( new GwtEventDelegateHandler< ED, UIEvent<ED> >( clickEvent ) );

    return button;
  }

  @Override
  public Button getRealComponent()
  {
    return button;
  }
  
  /*
   * the button is created when the ButtonUI loading, so the button hasn't created when addClickEvent
   */
  public void addClickEvent( UIEvent< ED > event )
  {
    clickEvent = event;
  }

}
