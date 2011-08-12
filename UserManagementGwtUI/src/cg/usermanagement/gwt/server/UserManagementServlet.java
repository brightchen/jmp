package cg.usermanagement.gwt.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cg.common.util.DataConverter;
import cg.gwt.components.server.resource.ResourceDataManager;
import cg.gwt.components.shared.data.MenuBarData;
import cg.gwt.components.shared.data.ResponseData;
import cg.gwt.components.shared.data.UIIdentity;
import cg.resourcemanagement.util.LocaleUtil;
import cg.services.session.SessionManager;
import cg.usermanagement.api.IUserService;
import cg.usermanagement.gwt.client.IUserManagement;
import cg.usermanagement.gwt.server.resource.UserManagementResourceDataBuilder;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.AddRoleData;
import cg.usermanagement.gwt.shared.data.ControlSectionData;
import cg.usermanagement.gwt.shared.data.LocaleMenuBarData;
import cg.usermanagement.gwt.shared.data.LocaleMenuItemData;
import cg.usermanagement.gwt.shared.data.SearchUserData;
import cg.usermanagement.gwt.shared.data.UserListData;
import cg.usermanagement.gwt.shared.data.UserLoginData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelOperation;
import cg.usermanagement.gwt.shared.data.UserManagementStartData;
import cg.usermanagement.gwt.shared.data.UserRegisterData;
import cg.usermanagement.model.view.PermissionView;
import cg.usermanagement.model.view.RoleView;
import cg.usermanagement.model.view.UserRegisterView;
import cg.usermanagement.shared.LoginException;
import cg.usermanagement.shared.RegisterUserException;
import cg.usermanagement.shared.RoleException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserManagementServlet extends RemoteServiceServlet implements IUserManagement
{
  private static final long serialVersionUID = -8926280156981738193L;
  private static final String SESSION_ID_KEY = "session_id";
  
  private Logger log = Logger.getLogger( UserManagementServlet.class );
  
  private IUserService userService;
  
  
  public void init() throws ServletException 
  {
    ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    // Get the value of an initialization parameter
    String userServiceName = getServletConfig().getInitParameter( "userService" );
    setUserService( context.getBean( userServiceName, IUserService.class ) ); 
  }

  public void setUserService( IUserService userService )
  {
    this.userService = userService;
  }
  public IUserService getUserService()
  {
    return userService;
  }

  public Locale getCurrentLocale()
  {
    adjustSessionManager();
    Locale locale = (Locale)SessionManager.getAttribute( UserManagementSessionKey.locale );
    return locale == null ? LocaleUtil.TOP_LOCALE : locale;
  }
  
  public ResponseData< ControlSectionData > buildControlSectionData()
  {
    return buildControlSectionData( getCurrentLocale() );
  }
  
  public ResponseData< ControlSectionData > buildControlSectionData( Locale locale )
  {
    ResponseData< ControlSectionData > rd = new ResponseData< ControlSectionData >();
    rd.setFlowData( UIIdentity.CONTROL_SECTION );
    
    MenuBarData menuBarData = new LocaleMenuBarData(); 
    ResourceDataManager.defaultInstance.fillResourceDatas( locale, menuBarData, true );
    fillLocaleMenuItems( menuBarData );
    
    ControlSectionData controlSectionData = new ControlSectionData();
    controlSectionData.addMenuBarData( menuBarData );
    rd.setContentData( controlSectionData );

    return rd;
  }
  
  public List< ResponseData<?> > getStartUI( String localeName )
  {
    SessionManager.startSession();
    Locale locale = getCurrentLocale();

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
      ResourceDataManager.defaultInstance.fillResourceDatas( locale, data, true );
      rd.setContentData( data );
      
      rds.add( rd );
    }    

    
    //put into the session
    String sessionId = SessionManager.startSession();
    setSessionId( sessionId );
    SessionManager.putAttribute( UserManagementSessionKey.currentPageDatas, rds );

    return rds;
  }

  /*
   * the locale menu item is different from typical menu item as the resource data is not depended on the locale at all.
   * this method fill the information of these locale menu items
   */
  protected void fillLocaleMenuItems( MenuBarData menuBarData )
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
  /*
   * when the locale changed, the server should response with resource data of new locale and refresh the page
   * @see cg.usermanagement.gwt.client.IUserManagement#changeLocale(java.lang.String)
   */
  @Override
  @SuppressWarnings( "unchecked" )
  public List< ResponseData<?> > changeLocale( String localeName )
  {
    Locale locale = LocaleUtil.getLocale( localeName );
    adjustSessionManager();
    SessionManager.putAttribute( UserManagementSessionKey.locale, locale );

    List< ResponseData<?> > rds = (List< ResponseData<?> >)SessionManager.getAttribute( UserManagementSessionKey.currentPageDatas );
    if( rds == null || rds.size() == 0 )
      return rds;
    for( ResponseData<?> rd : rds )
    {
      ResourceDataManager.defaultInstance.fillResourceDatas( locale, rd.getContentData(), false );
      
      //handle locale menu specially
      if( UIIdentity.CONTROL_SECTION.equals( rd.getFlowData().getUiIdentity() ) )
        fillLocaleMenuItems( ( (ControlSectionData)rd.getContentData() ).getMenuPanelData().get( 0 ) );
    }
    return rds;
  }
  
  public List< ResponseData<?> > userlogin( String userName, String password ) throws LoginException
  {
    log.debug( "login: user=" + userName + "; password=" + password );
    if( password == null || password.isEmpty() )
      throw new LoginException( LoginException.LOGIN_ERROR.PASSWORD_EMTPY );
    
    IUserService service = getUserService();
    Set< PermissionView > permissions = service.userLogin( userName, password );

    adjustSessionManager();
    SessionManager.putAttribute( UserManagementSessionKey.userName, userName );

    // cache the user permissions in the session as it is a very frequently used 
    SessionManager.putAttribute( UserManagementSessionKey.userPermissions, permissions );
    
    List< ResponseData<?> > responseDatas = getUserManagementPanelDatas();
    SessionManager.putAttribute( UserManagementSessionKey.currentPageDatas, responseDatas );
    
    return responseDatas;
  }

  //it should be account instead of user login
  @Override
  public List< ResponseData<?> > accountlogin( String accountName, String password ) throws LoginException
  {
    log.debug( "login: account=" + accountName + "; password=" + password );
    if( password == null || password.isEmpty() )
      throw new LoginException( LoginException.LOGIN_ERROR.PASSWORD_EMTPY );
    
    IUserService service = getUserService();
    Set< PermissionView > permissions = service.accountLogin( accountName, password );

    adjustSessionManager();
    SessionManager.putAttribute( UserManagementSessionKey.accountName, accountName );
    
    // cache the role permissions in the session as it is a very frequently used 
    SessionManager.putAttribute( UserManagementSessionKey.accountPermissions, permissions );
    
    List< ResponseData<?> > responseDatas = getUserManagementPanelDatas();
    SessionManager.putAttribute( UserManagementSessionKey.currentPageDatas, responseDatas );
    
    return responseDatas;

  }
  
  /*
   * get the response data to let the client build User Management Panel
   */
  public List< ResponseData<?> > getUserManagementPanelDatas()
  {
    List< ResponseData<?> > rds = new ArrayList< ResponseData<?> >();
    
    // control section data
    {
      rds.add( buildControlSectionData() );
    }
    
     
    {
      ResponseData< UserManagementPanelData > rd = new ResponseData< UserManagementPanelData >();
      rd.setFlowData( UIIdentity.UM_CONTROL_PANEL );

      UserManagementPanelData data = new UserManagementPanelData();
      ResourceDataManager.defaultInstance.fillResourceDatas( getCurrentLocale(), data, true );
      rd.setContentData( data );
      
      rds.add( rd );
    }    
    
    return rds;
  }
  
  @Override
  public void registerUser( UserRegisterData data ) throws RegisterUserException
  {
    log.debug( "registerUser" );
    String userName = data.getUserName();
    if( userName == null || userName.isEmpty() )
      throw new RegisterUserException( RegisterUserException.REGISTER_USER_ERROR.USERNAME_EMTPY );
    String password = data.getPassword();
    if( password == null || password.isEmpty() )
      throw new RegisterUserException( RegisterUserException.REGISTER_USER_ERROR.PASSWORD_EMTPY );
    
    IUserService service = getUserService();   
    UserRegisterView view = new UserRegisterView();
    DataConverter.shallowCopyConvert( data, view );
    service.registerUser( view );
  }
  
  @Override
  public List< ResponseData<?> > onUserManagementPanelOperation( UserManagementPanelOperation operation )
  {
    List< ResponseData<?> > rds = new ArrayList< ResponseData<?> >();

    if( UserManagementPanelOperation.SearchUser.equals( operation ) )
    {
    }
    if( UserManagementPanelOperation.SearchAccount.equals( operation ) )
    {
    }
    if( UserManagementPanelOperation.AddAccount.equals( operation ) )
    {
    }
    if( UserManagementPanelOperation.SearchRole.equals( operation ) )
    {
    }
  
    if( UserManagementPanelOperation.AddRole.equals( operation ) )
    {
      ResponseData< AddRoleData > rd = new ResponseData< AddRoleData >();
      rd.setFlowData( UIIdentity.UM_ADD_ROLE );

      AddRoleData data = new AddRoleData();
      ResourceDataManager.defaultInstance.fillResourceDatas( getCurrentLocale(), data, true );
      rd.setContentData( data );
      
      rds.add( rd );
      
      return rds;
    }
    if( UserManagementPanelOperation.AddPermission.equals( operation ) )
    {
    }

    return null;
  }
  
  @Override
  public long addRole( String roleName ) throws RoleException
  {
    IUserService service = getUserService();
    RoleView roleView = service.addRole( roleName );
    return roleView.getId();
  }
  
  public List< UserListData > searchUser( SearchUserData searchUserData )
  {
    return null;
  }
  
  protected void adjustSessionManager()
  {
    String sessionId = getSessionId();
    if( sessionId == null )
      return;
    SessionManager.adjust( sessionId );
  }
  protected String getSessionId()
  {
    HttpSession session = getThreadLocalRequest().getSession(true);
    String sessionId = ( String )session.getAttribute( SESSION_ID_KEY );
    return sessionId;
  }
  public void setSessionId( String sessionId )
  {
    HttpSession session = getThreadLocalRequest().getSession(true);
    session.setAttribute( SESSION_ID_KEY, sessionId );
    
  }

}
