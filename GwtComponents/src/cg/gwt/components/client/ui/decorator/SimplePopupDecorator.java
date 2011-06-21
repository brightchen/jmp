package cg.gwt.components.client.ui.decorator;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/*
 * the data is the title of the popup
 */
public class SimplePopupDecorator< UC extends Widget > extends PopupDecorator< String, UC >
{

  public SimplePopupDecorator( String data, UC decoratedComponent )
  {
    super( data, decoratedComponent );
  }

  @Override
  protected PopupPanel buildDecoratorComponent()
  {
    PopupPanel popup = new PopupPanel();
    popup.setTitle( getData() );
    return popup;
  }

}
