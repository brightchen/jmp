package cg.gwt.components.client.ui.decorator;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class PopupDecorator< D, UC extends Widget > extends Decorator< D, UC, PopupPanel >
{
  public PopupDecorator( D data, UC decoratedComponent )
  {
    super( data, decoratedComponent );
    setDecoratorComponent( buildDecoratorComponent() );
  }

  protected PopupPanel buildDecoratorComponent()
  {
    return new PopupPanel();
  }
}
