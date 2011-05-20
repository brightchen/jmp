package cg.gwt.components.client.ui;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class UIPanelComposite< D, U extends Panel > extends UIComposite< D, U >
{

  @Override
  protected void addChildToContainer( U theContainer, Widget child, int index )
  {
    theContainer.add( child );
  }
}
