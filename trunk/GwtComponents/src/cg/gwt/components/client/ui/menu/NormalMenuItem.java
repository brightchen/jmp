package cg.gwt.components.client.ui.menu;

import cg.gwt.components.shared.data.NormalMenuItemData;

import com.google.gwt.user.client.ui.MenuItem;

public class NormalMenuItem extends MenuItem
{
  public NormalMenuItem( NormalMenuItemData data )
  {
    super( data.getTitle(), new TypicalCommand( data.getCommandKey() ) );
  }

}
