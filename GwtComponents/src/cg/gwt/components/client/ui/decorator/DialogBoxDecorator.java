package cg.gwt.components.client.ui.decorator;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class DialogBoxDecorator< D, UC extends Widget > extends PopupDecorator< D, UC >
{
  public DialogBoxDecorator( D data, UC decoratedComponent )
  {
    super( data, decoratedComponent );
  }
  
  @Override
  protected PopupPanel buildDecoratorComponent()
  {
    return new DialogBox();
  }

}
