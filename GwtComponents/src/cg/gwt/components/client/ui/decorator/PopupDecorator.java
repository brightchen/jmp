package cg.gwt.components.client.ui.decorator;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class PopupDecorator< D, UC extends Widget > extends Decorator< D, UC, PopupPanel >
{
  public PopupDecorator( D data )
  {
    super( data );
  }
  
  public PopupDecorator( D data, UC decoratedComponent )
  {
    super( data, decoratedComponent );
    setDecoratorComponent( buildDecoratorComponent() );
  }
  
  @Override
  protected PopupPanel buildContainer()
  {
    return buildDecoratorComponent();
  }

  protected PopupPanel buildDecoratorComponent()
  {
    return new PopupPanel();
  }
  
  public void centre()
  {
    //build the UI before show
    build();
    getDecoratorComponent().center();
  }
  
  public void hide( boolean autoClose )
  {
    getDecoratorComponent().hide( autoClose );
  }

  public void show()
  {
    //build the UI before show
    build();
    getDecoratorComponent().show();
  }

}
