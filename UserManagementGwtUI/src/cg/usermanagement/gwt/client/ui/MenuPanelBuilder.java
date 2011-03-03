package cg.usermanagement.gwt.client.ui;

import java.util.ArrayList;
import java.util.List;

import cg.gwt.components.client.ui.UIObjectBuilder;
import cg.usermanagement.gwt.shared.data.MenuBarData;

import com.google.gwt.user.client.ui.MenuBar;

//The MenuBarBuilder only build menu bar with one root
//this builder build menu bar with one or multiple roots
public class MenuPanelBuilder extends UIObjectBuilder< List< MenuBarData >, MenuBar >
{
  @Override
  public MenuBar build()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected List< MenuBarData > createData()
  {
    return new ArrayList< MenuBarData >();
  }

}
