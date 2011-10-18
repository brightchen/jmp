package cg.usermanagement.gwt.client;

import java.util.List;

import cg.contentdata.shared.UIContentData;
import cg.gwt.components.client.ui.EmptyUI;
import cg.gwt.components.client.ui.PanelCompositeUI;
import cg.gwt.components.shared.data.ResponseData;
import cg.gwt.components.shared.data.UIFlowData;
import cg.gwt.components.shared.data.UIIdentity;
import cg.usermanagement.gwt.client.role.RoleDetailUI;
import cg.usermanagement.gwt.client.user.SearchUserUI;
import cg.usermanagement.gwt.shared.data.ControlSectionData;
import cg.usermanagement.gwt.shared.data.LoginData;
import cg.usermanagement.gwt.shared.data.RoleDetailData;
import cg.usermanagement.gwt.shared.data.SearchUserData;
import cg.usermanagement.gwt.shared.data.UserListData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelOperation;
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
  private static SectionUI clientSectionUI = null;
  private static SectionUI userManagementPanelSectionUI = null;

  
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
  public static void refreshPage( List< ResponseData<?> > responseDatas )
  {
    if( pageUI == null )
      buildPage();
    
    for( ResponseData data : responseDatas )
    {
      UIFlowData flowData = data.getFlowData();
      UIIdentity identity = flowData.getUiIdentity();
      if( UIIdentity.CONTROL_SECTION.equals( identity ) )
      {
        //generic control section, maybe used by other module(s)
        controlSectionUI.setComponent( buildUI( data ) );
        controlSectionUI.refresh();
      }
      else if( UIIdentity.UM_CONTROL_PANEL.equals( identity ) )
      {
        //user management control panel, for user management module only
        userManagementPanelSectionUI.setComponent( buildUI( data ) );
        userManagementPanelSectionUI.refresh();
      }
      else
      {
        clientSectionUI.setComponent( buildUI( data ) );    //the clientSectionUI should support multiple UI?
        clientSectionUI.refresh();
      }
    }
  }
  
  public static void refreshClientSection( Widget widget )
  {
    clientSectionUI.setComponent( widget );
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

    throw new IllegalStateException( "Invalid UIIdentity. " );
  }
  
  public static void onGetStartUISuccess( List< ResponseData<?> > responseDatas )
  {
    refreshPage( responseDatas );
  }

  public static void onLoginSuccess( LoginData loginData, List< ResponseData<?> > responseDatas )
  {
    refreshPage( responseDatas );
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
  
  protected static void onUserManagementPanelOperationSuccess( UserManagementPanelOperation operation, List< ResponseData<?> > responseDatas )
  {
    refreshPage( responseDatas );
  }
  
  public static void onSearchUserSuccess( List< UserListData > userListDatas )
  {
    
  }
  
  public static void onUserManagementPanelSearchAccount()
  {
    
  }

  public static void onUserManagementPanelAddAccount()
  {
    
  }

  public static void onUserManagementPanelSearchRole()
  {
    
  }

  public static void onUserManagementPanelAddPermission()
  {
    
  }

  /*
   * switch to role detail ui to assign permissions
   */
  public static void onSaveRoleSuccess( RoleDetailData roleDetailData )
  {
    refreshClientSection( buildRoleDetailUI( roleDetailData ) );
  }
  
  
  public static Widget buildRoleDetailUI( RoleDetailData roleDetailData )
  {
    RoleDetailUI roleDetailUI = new RoleDetailUI( roleDetailData );
    return roleDetailUI;
  }
}
