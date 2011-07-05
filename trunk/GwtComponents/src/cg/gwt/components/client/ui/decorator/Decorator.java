package cg.gwt.components.client.ui.decorator;

import java.util.List;

import cg.gwt.components.client.ui.PanelCompositeUI;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/*
 * The Decorator can be looked as a composite which container is the decorator and the decorated component is the only child of the composite
 * UC is the UIComponent which decorated by the decorator
 * 
 */
public class Decorator< D, UC extends Widget, U extends Panel > extends PanelCompositeUI< D, U >
{
  public Decorator( D data )
  {
    setData( data );
  }
  public Decorator( D data, UC decoratedComponent )
  {
    setData( data );
    setDecoratedComponent( decoratedComponent );
  }
  
  public void setDecoratedComponent( UC decoratedComponent )
  {
    //only has one child. So clear the children in case duplicate
    clearChildren();
    addChild( decoratedComponent );
  }
  
  @SuppressWarnings( "unchecked" )
  public UC getDecoreatedComponent()
  {
    List< Widget > children = getChildren();
    //only has one child
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
