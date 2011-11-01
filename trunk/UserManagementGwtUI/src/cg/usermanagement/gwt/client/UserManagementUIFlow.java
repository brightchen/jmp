package cg.usermanagement.gwt.client;

import java.util.ArrayList;
import java.util.List;

import cg.contentdata.shared.UIContentData;
import cg.gwt.components.client.ui.EmptyUI;
import cg.gwt.components.client.ui.PanelCompositeUI;
import cg.gwt.components.client.ui.grid.GridUI;
import cg.gwt.components.shared.data.Frame;
import cg.gwt.components.shared.data.FrameData;
import cg.gwt.components.shared.data.ResponseData;
import cg.gwt.components.shared.data.UIFlowData;
import cg.gwt.components.shared.data.UIIdentity;
import cg.usermanagement.gwt.client.role.RoleDetailUI;
import cg.usermanagement.gwt.client.user.SearchUserUI;
import cg.usermanagement.gwt.shared.data.ControlSectionData;
import cg.usermanagement.gwt.shared.data.ListUsersGridData;
import cg.usermanagement.gwt.shared.data.RoleDetailData;
import cg.usermanagement.gwt.shared.data.SearchUserData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelData;
import cg.usermanagement.gwt.shared.data.UserManagementStartData;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/*
 * the web server side will control the UI flow instead the client side.
 * for the first page, the UIFlow from the client will send a ajax call to the server side to get the startPage
 * for every request send to the web server, the response will contain the information of next UI ( include the required data )
 * 
 * interface between web client and server:
 * 1. the web server control the page flow, the data fetched from the server decide which page/UIs should be displayed
 * 2. the user management page can be divided into 3 section: 
 *   control section: this is the common section could be used for all modules
 *   user management panel: this is the common section for user management module
 *   client section: this is the section displaying current operation/component.
 *   
 * In most case, only the client section need to be refreshed. But the user management panel or control section
 * should also be refresh-able. the system should define the interface which supports both.
 * 
 * the response data list only contains the sections which need to be refreshed.
 */
@SuppressWarnings( "rawtypes" ) 
public class UserManagementUIFlow
{
  //the static attribute is safe as this is client code and run in the web browser.
  //the static is only static for one client web browser.
//  private static UserManagementControlSectionUI controlSectionUI;
//  private static ClientSectionUI clientSectionUI = new ClientSectionUI();
  
  //it should only have one popup at any time
//  private static PopupDecorator< ?, Widget > currentPopup;    
//  private static PopupDecorator<?,?> roleDetailPopup;
  
  private static final String cookieLocale = "locale";
  
  private static PanelCompositeUI pageUI = null;
  private static SectionUI controlSectionUI = null;
  private static SectionUI< VerticalPanel > clientSectionUI = null;
  private static SectionUI userManagementPanelSectionUI = null;
  private static FrameData currentFrameData = null;

  
  public static void start()
  {
    String localeName = Cookies.getCookie( cookieLocale );
    if( localeName == null || localeName.isEmpty() )
    {
      localeName = UserManagementStartEvent.LOCALE.en_US.name();
      Cookies.setCookie( cookieLocale, localeName );
    }
    
    UserManagementStartEvent event = new UserManagementStartEvent();
    event.setLocale( localeName );
    event.fire();
  }
  
  public static void buildPage()
  {
    if( pageUI != null )
      return;
    controlSectionUI = new SectionUI();
    clientSectionUI = new SectionUI();
    userManagementPanelSectionUI = new SectionUI();

    //have both control section and client section
    pageUI = new PanelCompositeUI();
    pageUI.setContainer( new VerticalPanel() );
    pageUI.addChild( controlSectionUI );
    pageUI.addChild( userManagementPanelSectionUI );
    pageUI.addChild( clientSectionUI );
    
    // the UI's onLoad will be called when adding this component to RootPanel(), which calls build() method.
    // so, all the sub-componenets must be added to the pageUI before it added to the RootPanel();
    RootPanel.get().clear();
    RootPanel.get().add( pageUI );

  }
  
  /*
   * the responseDatas should be control section data and the data for the client section
   */
  @SuppressWarnings( "unchecked" )
  public static void refreshPage( FrameData frameData )
  {
    if( pageUI == null )
      buildPage();
//    final Frame frame = frameData.getFrame();
//    final boolean isInheritable = frame.isInheritable( currentFrame );
    List< ResponseData<?> > responseDatas = frameData.getResponseDatas();
    List< ResponseData<?> > clientSectionResponseDatas = new ArrayList< ResponseData<?> >();
    
    //handle none client section datas first;
    for( ResponseData data : responseDatas )
    {
      if( handleNoneClientSectionResponseData( frameData, data ) )
        continue;
      
      // this is the client section data;
      clientSectionResponseDatas.add( data );
    }
    
    //handle client section datas
    buildClientSection( frameData, clientSectionResponseDatas );
    clientSectionUI.refresh();
    currentFrameData = frameData;
  }
  
  /**
   * handle the none client section response data
   * @param frameData
   * @param responseData
   * @return true if the response data is none client section data
   */
  public static boolean handleNoneClientSectionResponseData( FrameData frameData, ResponseData responseData )
  {
    UIFlowData flowData = responseData.getFlowData();
    UIIdentity identity = flowData.getUiIdentity();
    if( UIIdentity.CONTROL_SECTION.equals( identity ) )
    {
      //generic control section, maybe used by other module(s)
      controlSectionUI.setComponent( buildUI( responseData ) );
      controlSectionUI.refresh();
      return true;
    }
    
    if( UIIdentity.UM_CONTROL_PANEL.equals( identity ) )
    {
      //user management control panel, for user management module only
      userManagementPanelSectionUI.setComponent( buildUI( responseData ) );
      userManagementPanelSectionUI.refresh();
      return true;
    }
    
    return false;
//    else
//    {
//      //the client section can contain several UIs/responses
//      handleClientSectionResponse( frameData, responseData );
//      clientSectionUI.setComponent( buildUI( responseData ) );    //the clientSectionUI should support multiple UI?
//      clientSectionUI.refresh();
//    }    
  }
  
  public static void buildClientSection( FrameData frameData, List< ResponseData<?> > responseDatas )
  {
    Frame frame = frameData.getFrame();
    Frame currentFrame = currentFrameData == null ? null : currentFrameData.getFrame();
    boolean isInheritable = frame.isInheritable( currentFrame );
    //all the client section real component using VerticalPanel now, refactor it later
    VerticalPanel realClientSectionUI = null;
    if( isInheritable )
      realClientSectionUI = clientSectionUI.getComponent();
    else
    {
      realClientSectionUI = new VerticalPanel();
      clientSectionUI.setComponent( realClientSectionUI );
    }
    for( ResponseData data : responseDatas )
    {
      realClientSectionUI.add( buildUI( data ) );
    }
  }
  
  /**
   * the client section maybe composed by several ResponseData
   * when handle this responseData, only update the UI-section which corresponding to this responseData
   * @param frame
   * @param responseData
   */
  public static void handleClientSectionResponse( FrameData frameData, ResponseData responseData )
  {
    
  }
  
  public static Widget buildUI( ResponseData<?> responseData )
  {
    UIFlowData flowData = responseData.getFlowData();
    UIIdentity identity = flowData.getUiIdentity();
    UIContentData contentData = responseData.getContentData();
    
    if( UIIdentity.CONTROL_SECTION.equals( identity ) )
    {
      return new UserManagementControlSectionUI( (ControlSectionData)contentData );
    }
    if( UIIdentity.UM_START.equals( identity ) )
    {
      return new UserManagementStartUI( (UserManagementStartData)contentData );   
    }
    if( UIIdentity.UM_CONTROL_PANEL.equals( identity ) )
    {
      return new UserManagementPanelUI( (UserManagementPanelData)contentData ); 
    }
    if( UIIdentity.UM_EMPTY.equals( identity ) )
    {
      return EmptyUI.instance();
    }
    if( UIIdentity.UM_SEARCH_USER.equals( identity ) )
    {
      return new SearchUserUI( (SearchUserData)contentData );
    }
    if( UIIdentity.UM_SEARCH_ACCOUNT.equals( identity ) )
    {
    }
    if( UIIdentity.UM_ADD_ACCOUNT.equals( identity ) )
    {
    }
    if( UIIdentity.UM_SEARCH_ROLE.equals( identity ) )
    {
    }
    if( UIIdentity.UM_ADD_ROLE.equals( identity ) )
    {
      return new RoleDetailUI( (RoleDetailData)contentData );
    }
    if( UIIdentity.UM_ADD_PERMISSION.equals( identity ) )
    {
    }
    if( UIIdentity.UM_LIST_USERS.equals( identity ) )
    {
      return new GridUI( (ListUsersGridData)contentData );
    }

    throw new IllegalStateException( "Invalid UIIdentity: " +  identity.name() );
  }
  
  /*
   * this is the UI allow user the manage users,
   * such as user account management; account role management; role permission management etc
   */
//  public static ComponentUI< ?, ? > buildUserManagementPanelUI()
//  {
//    UserManagementPanelData data = new UserManagementPanelData();
//    for( UserManagementButtonMeta meta : UserManagementButtonMeta.values() )
//    {
//      data.addButtonData( meta.getButtonData() );
//    }
//    return new UserManagementPanelUI( data );
//  }
}
