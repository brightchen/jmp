package cg.usermanagement.gwt.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cg.contentdata.management.ResourceDataManager;
import cg.contentdata.shared.UIContentData;
import cg.gwt.components.shared.data.Frame;
import cg.gwt.components.shared.data.FrameData;
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
public class ResponseUtil
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
  
  public static FrameData buildStartUI( Locale locale )
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
    
    FrameData frameRd = new FrameData( Frame.UMF_START );
    frameRd.setResponseDatas( rds );
    return frameRd;
  }
  
  /*
   * get the response data to let the client build User Management Panel
   */
  public static FrameData getUserManagementPanelDatas( Locale locale )
  {
    List< ResponseData<?> > rds = new ArrayList< ResponseData<?> >();
    
    // control section data
    {
      rds.add( buildControlSectionData( locale ) );
    }
    
    // UM_CONTROL_PANEL
    {
      ResponseData< UserManagementPanelData > rd = new ResponseData< UserManagementPanelData >();
      rd.setFlowData( UIIdentity.UM_CONTROL_PANEL );

      UserManagementPanelData data = new UserManagementPanelData();
      ResourceDataManager.defaultInstance.injectResourceDatas( locale, data, true );
      rd.setContentData( data );
      
      rds.add( rd );
    }    
    
    // the client section is emtpy
    {
      ResponseData rd = new ResponseData();
      rd.setFlowData( UIIdentity.UM_EMPTY );
      rds.add( rd );
    }
    
    return new FrameData( Frame.UMF_CONTROL_PANEL, rds );
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
  
  /**
   * get the response data of the section which represented by uiIdentity from responseDatas
   * @param responseDatas
   * @param uiIdentity UI section which represented by UIIdentity
   * @return the ResponseData found, null if not found
   */
  public static ResponseData<?> getResponseData( List< ResponseData<?> > responseDatas, UIIdentity uiIdentity )
  {
    if( responseDatas == null || responseDatas.isEmpty() )
      return null;
    for( ResponseData<?> rd : responseDatas )
    {
      if( uiIdentity.equals( rd.getFlowData().getUiIdentity() ) )
        return rd;
    }
    return null;
  }
  
  public static void updateClientSectionResponseDataToSession( ResponseData<?> clientSectionResponseData )
  {
    FrameData frameData = getCurrentFrameDataFromSession();
    // the frame data must already put into session when calling this method
    frameData.mergeResponseData( clientSectionResponseData );
  }

  /**
   * set the response datas to the session, this is useful to fresh current page, such as when location changed 
   * compatible with the UI display, only update the section which listed in the responseDatas
   * @param responseDatas:
   */
  public static void updateResponseDatasToSession( FrameData frameData )
  {
    List<ResponseData<?>> responseDatas = frameData.getResponseDatas();
    if( responseDatas == null || responseDatas.isEmpty() )
      return;

    FrameData previousFrameData = (FrameData)SessionManager.getAttribute( UserManagementSessionKey.currentFrameData );
    if( previousFrameData != null )
    {
      frameData.merger( previousFrameData.getResponseDatas() );
    }
 
    SessionManager.putAttribute( UserManagementSessionKey.currentFrameData, frameData );
  }
  
  public static FrameData getCurrentFrameDataFromSession()
  {
    return (FrameData)SessionManager.getAttribute( UserManagementSessionKey.currentFrameData );
  }

  /**
   * get response data from lists of response datas according to UIIdentity
   * @param identity
   * @param responseDataLists
   * @return
   */
  public static ResponseData getResponseDataByIdentity( UIIdentity identity, List< ResponseData<?> > ... responseDataLists )
  {
    if( identity == null || responseDataLists == null || responseDataLists.length == 0 )
      return null;
    
    for( List< ResponseData<?> > rdList : responseDataLists )
    {
      for( ResponseData<?> rd : rdList )
      {
        if( identity.equals( rd.getFlowData().getUiIdentity() ) )
        {
          return rd;
        }
      }
    }
    return null;
  }
  
  public static < D extends UIContentData > List< ResponseData<?> > toGenericResponseDataList( List< ResponseData<D> > rdList )
  {
    if( rdList == null || rdList.isEmpty() )
      return Collections.emptyList();
    
    List< ResponseData<?> > genericRdList =new ArrayList< ResponseData<?> >();
    for( ResponseData<D> rd : rdList )
    {
      genericRdList.add( rd );
    }
    return genericRdList;
  }
}
