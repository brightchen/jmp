package cg.gwt.components.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/*
 * UIComposite is a type of Widget which can build by its data and children
 * U is the Widget we want to build
 * D is the data which required for building container
 */
public class UIComposite< D, U extends Panel > extends UIComponent< D, U >
{
  private List< Widget > children = new ArrayList<Widget>();
  private U container;
  
  public UIComposite(){}
  
  public UIComposite( U container )
  {
    setContainer( container );
  }
  
  public UIComposite( D data, U container )
  {
    this( container );
    setData( data );
  }
  
  /*
   * the container can be set by the client code
   * it should be override by the sub-classes if the sub-class's container does NOT set by the client code
   */
  protected U buildContainer()
  {
    if( container != null )  
      return applyDataToContainer( container );
    return null;
  }
  
  protected U applyDataToContainer( U theContainer )
  {
    return theContainer;
  }
  
  @Override
  public U build()
  {
    buildContainer();
    beforeAddingChildren();
    int index = 0;
    for( Widget child : children )
    {
      addChildComponent( child, index++ );
    }
    afterAddingChildren();
    
    return container;
  }
  
  @Override
  public U getRealComponent()
  {
    return getContainer();
  }
  
  
  protected void beforeAddingChildren(){}
  protected void afterAddingChildren(){}
  
  protected void addChildComponent( Widget child, int index )
  {
    getContainer().add( child );
  }

  public void clearChildren()
  {
    children.clear();
  }
  
  public List< Widget > getChildren()
  {
    return children;
  }

  public void setChildren( List< Widget > children )
  {
    this.children = children;
  }

  public void addChild( Widget child )
  {
    children.add( child );
  }

  public U getContainer()
  {
    if( container == null )
      container = buildContainer();
    return container;
  }

  public void setContainer( U container )
  {
    this.container = container;
  }
  
}