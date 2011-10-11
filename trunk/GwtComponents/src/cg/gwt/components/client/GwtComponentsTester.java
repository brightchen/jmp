package cg.gwt.components.client;

import cg.gwt.components.client.ui.components.ButtonUI;
import cg.gwt.components.shared.data.ButtonData;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtComponentsTester implements EntryPoint
{
  public void onModuleLoad()
  {
    ButtonData buttonData = new ButtonData( "test", "test" );
    ButtonUI buttonui = new ButtonUI( buttonData );
    RootPanel.get().add( buttonui );
  }

}
