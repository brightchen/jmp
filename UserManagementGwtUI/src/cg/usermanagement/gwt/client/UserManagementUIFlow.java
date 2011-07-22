package cg.usermanagement.gwt.client;

import java.util.List;

import cg.gwt.components.client.ui.ComponentUI;
import cg.gwt.components.client.ui.decorator.PopupDecorator;
import cg.gwt.components.client.ui.decorator.PopupWithCancelButtonDecorator;
import cg.gwt.components.shared.data.ButtonData;
import cg.gwt.components.shared.data.ResponseData;
import cg.gwt.components.shared.data.UIFlowData;
import cg.gwt.components.shared.data.UIIdentity;
import cg.usermanagement.gwt.client.role.AddRoleUI;
import cg.usermanagement.gwt.client.role.RoleDetailUI;
import cg.usermanagement.gwt.shared.data.AddRoleData;
import cg.usermanagement.gwt.shared.data.ControlSectionData;
import cg.usermanagement.gwt.shared.data.LoginData;
import cg.usermanagement.gwt.shared.data.RoleDetailData;
import cg.usermanagement.gwt.shared.data.UserManagementStartData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelData;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/*
 * the web server side will control the UI flow instead the client side.
 * for the first page, the UIFlow from the client will send a ajax call to the server side to get the startPage
 * for every request send to the web server, the response will contain the information of next UI ( include the required data )
 */
public class UserManagementUIFlow
{
  //the static attribute is safe as this is client code and run in the web browser.
  //the static is only static for one client web browser.
  private static UserManagementControlSectionUI controlSectionUI;
  private static ClientSectionUI clientSectionUI = new ClientSectionUI();
  
  private static PopupDecorator<?,?> addRolePopup;
  private static PopupDecorator<?,?> roleDetailPopup;
  
  private static final String cookieLocale = "locale";
  
  public static void freshCurrentPage( List< ResponseData<?> > responseDatas )
  {
    controlSectionUI = (UserManagementControlSectionUI)buildUI( responseDatas.get( 0 ) );
    
    clientSectionUI = new ClientSectionUI();
    clientSectionUI.setComponent( buildUI( responseDatas.get( 1 ) ) );
   
    RootPanel rp = RootPanel.get();
    rp.clear();
    rp.add( controlSectionUI );
    rp.add( clientSectionUI );

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
  
  public static void doGetStartUISuccess( List< ResponseData<?> > responseDatas )
  {
    controlSectionUI = (UserManagementControlSectionUI)buildUI( responseDatas.get( 0 ) );
    
    clientSectionUI.setComponent( buildUI( responseDatas.get( 1 ) ) );
   
    RootPanel rp = RootPanel.get();
    rp.add( controlSectionUI );
    rp.add( clientSectionUI );
  }
  
  public static Widget buildUI( ResponseData<?> responseData )
  {
    UIFlowData flowData = responseData.getFlowData();
    UIIdentity identity = flowData.getUiIdentity();
    
    if( UIIdentity.CONTROL_SECTION.equals( identity ) )
    {
      return new UserManagementControlSectionUI( (ControlSectionData)responseData.getContentData() );
    }
    if( UIIdentity.UM_START.equals( identity ) )
    {
      return new UserManagementStartUI( (UserManagementStartData)responseData.getContentData() );   
    }
    
    throw new IllegalStateException( "Invalid UIIdentity. " );
  }
  
  public static void onLoginSuccess( LoginData data )
  {
    RootPanel.get().clear();
    RootPanel.get().add( buildUserManagementPanelUI() );
  }


  /*
   * this is the UI allow user the manage users,
   * such as user account management; account role management; role permission management etc
   */
  public static ComponentUI< ?, ? > buildUserManagementPanelUI()
  {
    UserManagementPanelData data = new UserManagementPanelData();
    for( UserManagementButtonMeta meta : UserManagementButtonMeta.values() )
    {
      data.addButtonData( meta.getButtonData() );
    }
    return new UserManagementPanelUI( data );
  }
  
  public static void doSearchUser()
  {
    
  }
  
  public static void doSearchAccount()
  {
    
  }

  public static void doAddAccount()
  {
    
  }

  public static void doSearchRole()
  {
    
  }

  /*
   * the system behavior when user clicking the add role button
   */
  public static void doAddRole()
  {
    //the addRolePopup should be recreate even if addRolePopup is not null, 
    //as addRolePopup can be already closed( for example, one user create two roles ),
    //which made any operation to addRolePopup is invalid
    AddRoleData roleData = new AddRoleData();
    ButtonData buttonData = roleData.getSaveButtonData();
    buttonData.setText( "Add Role" );
    buttonData.setTitle( "Add a new Role" );
    addRolePopup = new PopupWithCancelButtonDecorator< AddRoleUI >( "Add Role", new AddRoleUI( roleData ) );
    addRolePopup.centre();
  }
  
  /*
   * switch to role detail ui to assign permissions
   */
  public static void onAddRoleSuccess( AddRoleData addRoleData )
  {
    addRolePopup.hide( true );
    
    roleDetailPopup = new PopupWithCancelButtonDecorator< Widget >( "Role Detail", buildRoleDetailUI( addRoleData.getId(), addRoleData.getName() ) );
    roleDetailPopup.centre();
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
