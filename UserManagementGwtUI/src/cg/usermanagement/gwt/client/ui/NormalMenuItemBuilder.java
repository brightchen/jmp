package cg.usermanagement.gwt.client.ui;

import cg.gwt.components.client.ui.old.UIObjectBuilder;
import cg.usermanagement.gwt.client.TypicalCommand;
import cg.usermanagement.gwt.shared.data.NormalMenuItemData;

import com.google.gwt.user.client.ui.MenuItem;

//the Menu.SEPARATOR is represented by MenuItemSeparator.
//this is no relationship between MenuItem and MenuItemSeparator. so, they can't be built by one builder
//this builder only build normal menu item
public class NormalMenuItemBuilder extends UIObjectBuilder< NormalMenuItemData, MenuItem >
{
  // As MenuBarData extends NormalMenuItemData, so the data might be a MenuBarData
  // this build only care NormalMenuItemData
  @Override
  public MenuItem build()
  {
    //the data should be set
    NormalMenuItemData data = getData();
    String title = data.getTitle();
    String commandKey = data.getCommandKey();
    return new MenuItem( title, new TypicalCommand( commandKey ) );
  }

  @Override
  protected NormalMenuItemData createData()
  {
    return new NormalMenuItemData();
  }

}
