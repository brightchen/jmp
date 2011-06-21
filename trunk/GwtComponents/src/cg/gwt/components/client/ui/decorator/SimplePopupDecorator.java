package cg.gwt.components.client.ui.decorator;

import com.google.gwt.user.client.ui.Widget;

public class SimplePopupDecorator< UC extends Widget > extends PopupDecorator< String, UC >
{

  public SimplePopupDecorator( String data, UC decoratedComponent )
  {
    super( data, decoratedComponent );
  }

}
