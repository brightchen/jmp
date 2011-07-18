package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.EventSpecificMenuItemData;
import cg.gwt.components.shared.data.MenuEventData;
import cg.usermanagement.gwt.client.menu.UserManagementMenuEvent;

public class LocaleMenuItemData extends EventSpecificMenuItemData implements Serializable
{
  private static final long serialVersionUID = -4225004675359361290L;

  public LocaleMenuItemData(){}
  
  public LocaleMenuItemData( String localeValue, String localeKey )
  {
    //use localeValue( from resource file ) as the title, commandkey is control$locale, localeKey( "", "en_US" ) as parameter
    super( localeValue, new UserManagementMenuEvent( new MenuEventData( UserManagementMenuKey.control$locale.getStringKey(), localeKey ) ) );
  }
}
