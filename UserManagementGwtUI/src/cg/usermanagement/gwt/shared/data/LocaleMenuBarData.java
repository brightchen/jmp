package cg.usermanagement.gwt.shared.data;

import cg.contentdata.annotation.IResourceDataClass;
import cg.gwt.components.shared.data.MenuBarData;
import cg.gwt.components.shared.data.MenuItemResourceData;
import cg.resourcemanagement.annotation.IResourceKey;

@IResourceKey( className = "localemenu" )
@IResourceDataClass( resourceDataClass = MenuItemResourceData.class )
public class LocaleMenuBarData extends MenuBarData
{
  private static final long serialVersionUID = 3755289364335325032L;
}
