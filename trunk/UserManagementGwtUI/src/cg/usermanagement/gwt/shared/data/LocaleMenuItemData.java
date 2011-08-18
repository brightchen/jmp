package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.annotation.IResourceDataClass;
import cg.gwt.components.shared.data.MenuItemResourceData;
import cg.gwt.components.shared.data.NormalMenuItemData;
import cg.resourcemanagement.annotation.IResourceKey;

@IResourceKey( className = "localemenu" )
@IResourceDataClass( resourceDataClass = MenuItemResourceData.class )
public class LocaleMenuItemData extends NormalMenuItemData implements Serializable
{
  private static final long serialVersionUID = -4225004675359361290L;

  public LocaleMenuItemData(){}
  
  public LocaleMenuItemData( String localeValue, String localeKey )
  {
    //use localeValue( from resource file ) as the title, commandkey is control$locale, localeKey( "", "en_US" ) as parameter
    super( localeValue, UserManagementMenuKey.control$locale.getStringKey(), localeKey );
  }
}
