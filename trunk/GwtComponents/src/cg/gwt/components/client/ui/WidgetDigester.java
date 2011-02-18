package cg.gwt.components.client.ui;

import cg.gwt.components.shared.data.WidgetData;

import com.google.gwt.user.client.ui.Widget;

//the digester provides the function of digesting the data from the widget 
public abstract class WidgetDigester< T extends WidgetData, W extends Widget > implements IWidgetDigester
{
  //digest the data from widget
  @Override
  public abstract void digest();
}
