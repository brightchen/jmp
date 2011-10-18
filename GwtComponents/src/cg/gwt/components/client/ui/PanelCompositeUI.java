package cg.gwt.components.client.ui;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/*
 * the container of this composite is a panel
 */
public class PanelCompositeUI< D, U extends Panel > extends CompositeUI< D, U >
{
  /**
   * this UI can share the same container when refresh. 
   * so, the child should be cleared from container when refresh.
   */
  @Override
  protected void beforeAddingChildren()
  {
    if( !isContainerInjected() )
    {
      getContainer().clear();
    }
  }

  @Override
  protected void addChildToContainer( U theContainer, Widget child, int index )
  {
    theContainer.add( child );
  }
}
