package cg.gwt.components.client.ui.old;

import com.google.gwt.user.client.ui.Widget;

//the digester provides the function of digesting the data from the widget 
public abstract class WidgetDigester< T, W extends Widget > implements IUIObjectDigester
{
  //digest the data from widget
  @Override
  public abstract void digest();
}
