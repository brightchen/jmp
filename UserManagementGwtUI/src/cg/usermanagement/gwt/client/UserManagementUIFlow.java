package cg.usermanagement.gwt.client;

import java.util.List;

import cg.gwt.components.client.ui.PanelCompositeUI;
import cg.gwt.components.client.ui.decorator.PopupDecorator;
import cg.gwt.components.client.ui.decorator.PopupWithCancelButtonDecorator;
import cg.gwt.components.shared.data.ResponseData;
import cg.gwt.components.shared.data.UIContentData;
import cg.gwt.components.shared.data.UIFlowData;
import cg.gwt.components.shared.data.UIIdentity;
import cg.usermanagement.gwt.client.role.AddRoleUI;
import cg.usermanagement.gwt.client.role.RoleDetailUI;
import cg.usermanagement.gwt.shared.data.AddRoleData;
import cg.usermanagement.gwt.shared.data.ControlSectionData;
import cg.usermanagement.gwt.shared.data.LoginData;
import cg.usermanagement.gwt.shared.data.RoleDetailData;
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
 */
@SuppressWarnings( "rawtypes" ) 
public class UserManagementUIFlow
{
  //the static attribute is safe as this is client code and run in the web browser.
  //the static is only static for one client web browser.
//  private static UserManagementControlSectionUI controlSectionUI;
//  private static ClientSectionUI clientSectionUI = new ClientSectionUI();
  
  //it should only have one popup at any time
  private static PopupDecorator<?,?> currentPopup;    
//  private static PopupDecorator<?,?> roleDetailPopup;
  
  private static final String cookieLocale = "locale";
  
  public static void freshPage( List< ResponseData<?> > responseDatas )
  {
//    UserManagementControlSectionUI controlSectionUI = (UserManagementControlSectionUI)buildUI( responseDatas.get( 0 ) );
//    
//    ClientSectionUI clientSectionUI = new ClientSectionUI();
//    clientSectionUI.setComponent( buildUI( responseDatas.get( 1 ) ) );
   
    RootPanel rp = RootPanel.get();
    rp.clear();
    rp.add( buildUI( responseDatas ) );
//    rp.add( controlSectionUI );
//    rp.add( clientSectionUI );

  }
  
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
  
  
  /*
   * the responseDatas should be control section data and the data for the client section
   */
  @SuppressWarnings( "unchecked" ) 
  public static Widget buildUI( List< ResponseData<?> > responseDatas )
  {
    UserManagementControlSectionUI controlSectionUI = null;
    ClientSectionUI clientSectionUI = new ClientSectionUI();
    for( ResponseData data : responseDatas )
    {
      UIFlowData flowData = data.getFlowData();
      UIIdentity identity = flowData.getUiIdentity();
      if( UIIdentity.CONTROL_SECTION.equals( identity ) )
        controlSectionUI = (UserManagementControlSectionUI)buildUI( data );
      else
        clientSectionUI.setComponent( buildUI( data ) );    //the clientSectionUI should support multiple UI?
    }
    
    if( controlSectionUI == null )
      return clientSectionUI;

    //have both control section and client section
    PanelCompositeUI ui = new PanelCompositeUI();
    ui.setContainer( new VerticalPanel() );
    ui.addChild( controlSectionUI );
    ui.addChild( clientSectionUI );
    
    return ui;
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
    if( UIIdentity.UM_SEARCH_USER.equals( identity ) )
    {
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
      return new AddRoleUI( (AddRoleData)contentData );
    }
    if( UIIdentity.UM_ADD_PERMISSION.equals( identity ) )
    {
    }

    throw new IllegalStateException( "Invalid UIIdentity. " );
  }
  
  public static void doGetStartUISuccess( List< ResponseData<?> > responseDatas )
  {
    freshPage( responseDatas );
  }

  public static void onLoginSuccess( LoginData loginData, List< ResponseData<?> > responseDatas )
  {
    freshPage( responseDatas );
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
    currentPopup = new PopupWithCancelButtonDecorator< Widget >( "", buildUI( responseDatas ) );
    currentPopup.centre();
  }
  
  public static void onUserManagementPanelSearchUser()
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

  /*
   * the system behavior when user clicking the add role button
   */
//  public static void onUserManagementPanelAddRole()
//  {
//    //the addRolePopup should be recreate even if addRolePopup is not null, 
//    //as addRolePopup can be already closed( for example, one user create two roles ),
//    //which made any operation to addRolePopup is invalid
//    AddRoleData roleData = new AddRoleData();
//    ButtonData buttonData = roleData.getSaveButtonData();
//    ButtonResourceData resourceData = new ButtonResourceData();
//    resourceData.setText( "Add Role" );
//    resourceData.setTitle( "Add a new Role" );
//    buttonData.setResourceData( resourceData );
//    addRolePopup = new PopupWithCancelButtonDecorator< AddRoleUI >( "Add Role", new AddRoleUI( roleData ) );
//    addRolePopup.centre();
//  }

  public static void onUserManagementPanelAddPermission()
  {
    
  }

  /*
   * switch to role detail ui to assign permissions
   */
  public static void onAddRoleSuccess( AddRoleData addRoleData )
  {
    currentPopup.hide( true );
    
    currentPopup = new PopupWithCancelButtonDecorator< Widget >( "Role Detail", buildRoleDetailUI( addRoleData.getId(), addRoleData.getName() ) );
    currentPopup.centre();
  }
  
  
  public static Widget buildRoleDetailUI( Long roleId, String roleName )
  {
    RoleDetailData roleDetailData = new RoleDetailData();
    roleDetailData.setId( roleId );
    roleDetailData.setName( roleName );
    RoleDetailUI roleDetailUI = new RoleDetailUI( roleDetailData );
    return roleDetailUI;
  }
}
