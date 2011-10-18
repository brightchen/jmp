package cg.gwt.components.client.ui;

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


  @Override
  protected SimplePanel buildContainer()
  {
    return new SimplePanel();
  }

}
