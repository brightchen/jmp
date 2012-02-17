package cg.gwt.components.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/*
 * this composite only contains one component
 * U - the type of the component
 */
public class SimplePanelCompositeUI< U extends Widget > extends PanelCompositeUI< Void, SimplePanel >
{
  public SimplePanelCompositeUI() {}
  
  public SimplePanelCompositeUI( U component )
  {
    setComponent( component );
  }

  /*
   * only have one child
   */
  public void setComponent( U component )
  {
    getChildren().clear();
    if( component != null )
      addChild( component );
  }

  @SuppressWarnings( "unchecked")
  public U getComponent()
  {
    List< Widget > children = getChildren();
    return ( children == null || children.isEmpty() ) ? null : (U)children.get( 0 ); 
  }
  
  @Override
  protected SimplePanel buildContainer()
  {
    return new SimplePanel();
  }

}
