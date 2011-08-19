package cg.usermanagement.gwt.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cg.gwt.components.server.resource.ResourceDataManager;
import cg.gwt.components.shared.data.MenuBarData;
import cg.gwt.components.shared.data.ResponseData;
import cg.gwt.components.shared.data.UIIdentity;
import cg.services.session.SessionManager;
import cg.usermanagement.gwt.server.resource.UserManagementResourceDataBuilder;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.ControlSectionData;
import cg.usermanagement.gwt.shared.data.LocaleMenuBarData;
import cg.usermanagement.gwt.shared.data.LocaleMenuItemData;
import cg.usermanagement.gwt.shared.data.UserLoginData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelData;
import cg.usermanagement.gwt.shared.data.UserManagementStartData;
import cg.usermanagement.gwt.shared.data.UserRegisterData;

/*
 * this class provides methods to build the response to the Web client
 */
public class ResponseBuilder
{
  public static ResponseData< ControlSectionData > buildControlSectionData( Locale locale )
  {
    ResponseData< ControlSectionData > rd = new ResponseData< ControlSectionData >();
    rd.setFlowData( UIIdentity.CONTROL_SECTION );
    
    MenuBarData menuBarData = new LocaleMenuBarData(); 
    ResourceDataManager.defaultInstance.injectResourceDatas( locale, menuBarData, true );
    fillLocaleMenuItems( menuBarData );
    
    ControlSectionData controlSectionData = new ControlSectionData();
    controlSectionData.addMenuBarData( menuBarData );
    rd.setContentData( controlSectionData );

    return rd;
  }
  
  public static List< ResponseData<?> > buildStartUI( Locale locale )
  {
    SessionManager.startSession();

    List< ResponseData<?> > rds = new ArrayList< ResponseData<?> >();
    
    // control section data
    {
      rds.add( buildControlSectionData( locale ) );
    }
    
    // user management data
    {
      ResponseData< UserManagementStartData > rd = new ResponseData< UserManagementStartData >();
      rd.setFlowData( UIIdentity.UM_START );

      UserLoginData userLoginData = new UserLoginData();
//      userLoginData.setResourceData( UserManagementResourceDataBuilder.buildUserLoginResourceData( locale ) );
      
      AccountLoginData accountLoginData = new AccountLoginData();
//      accountLoginData.setResourceData( UserManagementResourceDataBuilder.buildAccountLoginResourceData( locale ) );
      
      UserRegisterData userRegisterData = new UserRegisterData();
      //set resource data later
      //userRegisterData.setResourceData( UserManagementResourceDataBuilder.b() );
      
      UserManagementStartData data = new UserManagementStartData( userLoginData, accountLoginData, userRegisterData );
      ResourceDataManager.defaultInstance.injectResourceDatas( locale, data, true );
      rd.setContentData( data );
      
      rds.add( rd );
    }    
    return rds;
  }
  
  /*
   * get the response data to let the client build User Management Panel
   */
  public static List< ResponseData<?> > getUserManagementPanelDatas( Locale locale )
  {
    List< ResponseData<?> > rds = new ArrayList< ResponseData<?> >();
    
    // control section data
    {
      rds.add( buildControlSectionData( locale ) );
    }
    
     
    {
      ResponseData< UserManagementPanelData > rd = new ResponseData< UserManagementPanelData >();
      rd.setFlowData( UIIdentity.UM_CONTROL_PANEL );

      UserManagementPanelData data = new UserManagementPanelData();
      ResourceDataManager.defaultInstance.injectResourceDatas( locale, data, true );
      rd.setContentData( data );
      
      rds.add( rd );
    }    
    
    return rds;
  }
  
  /*
   * the locale menu item is different from typical menu item as the resource data is not depended on the locale at all.
   * this method fill the information of these locale menu items
   */
  public static void fillLocaleMenuItems( MenuBarData menuBarData )
  {
    menuBarData.setMenuItemDatas( null );
    
    Map< String, String > localeDatas = UserManagementResourceDataBuilder.getSupportedLocalesData();
    for( Map.Entry< String, String > localeData : localeDatas.entrySet() )
    {
      //use locale name as the command key and locale name's resource value as menu item's title
      LocaleMenuItemData menuItemData = new LocaleMenuItemData( localeData.getValue(), localeData.getKey() );
      menuBarData.addMenuItemData( menuItemData );
    }
  }
}
