package cg.gwt.components.client.ui.old;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

//this builder simply add each widget into the panel
public class SimpleCompositeBuilder< P extends Panel > implements ICompositeBuilder< P >
{
  //the P is the empty Panel
  @Override
  public P build( P panel, Widget w1, Widget w2 )
  {
    if( w1 != null )
      panel.add( w1 );
    if( w2 != null )
      panel.add( w2 );
    return panel;
  }

}
