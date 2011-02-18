package cg.gwt.components.client.ui;

import com.google.gwt.user.client.ui.Widget;

//In fact, the original concept Part is really a builder.
//it build the widget use the data.
//So, refactor the Part to Builder.
//another concept is digest the data from the widget, the class for this processing is digester
public interface IWidgetBuilder< W extends Widget >
{
  //build the widget using data
  public W build();
  
  //call this method to fresh the widget when data modified
  //this method should be called after calling build()
  //the lazy loading can be supported by this method.
  public void refreshWidget();
}
