package cg.gwt.components.client.ui.decorator;

import java.util.List;

import cg.gwt.components.client.ui.UIComposite;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/*
 * The Decorator can be looked as a composite which container is the decorator and the decorated component is the only child of the composite
 * UC is the UIComponent which decorated by the decorator
 * 
 */
public class Decorator< D, UC extends Widget, U extends Panel > extends UIComposite< D, U >
{
  public void setDecoratedComponent( UC decoratedComponent )
  {
    clearChildren();
    addChild( decoratedComponent );
  }
  
  @SuppressWarnings( "unchecked" )
  public UC getDecoreatedComponent()
  {
    List< Widget > children = getChildren();
    return children == null ? null : ( (UC)children.get( 0 ) );
  }
  
  public void setDecoratorComponent( U decoratorComponent )
  {
    setContainer( decoratorComponent );
  }
  public U getDecoratorComponent()
  {
    return getContainer();
  }
  
}
