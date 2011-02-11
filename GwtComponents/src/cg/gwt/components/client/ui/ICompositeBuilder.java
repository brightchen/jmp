package cg.gwt.components.client.ui;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

//in GWT, Panel the widget which can contain other widget
public interface ICompositeBuilder< P extends Panel >
{
  //the P is the empty Panel
  public P build( P panel, Widget w1, Widget w2 );
}
