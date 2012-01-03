package cg.usermanagement.gwt.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cg.common.util.DataConverter;
import cg.common.util.EntityUtil;
import cg.contentdata.management.ResourceDataManager;
import cg.gwt.components.shared.data.Frame;
import cg.gwt.components.shared.data.FrameData;
import cg.gwt.components.shared.data.ResponseData;
import cg.gwt.components.shared.data.UIIdentity;
import cg.resourcemanagement.util.LocaleUtil;
import cg.services.session.SessionManager;
import cg.usermanagement.api.IUserService;
import cg.usermanagement.api.UserSearchCriteria;
import cg.usermanagement.gwt.client.IUserManagement;
import cg.usermanagement.gwt.shared.data.ControlSectionData;
import cg.usermanagement.gwt.shared.data.RoleDetailData;
import cg.usermanagement.gwt.shared.data.SearchUserData;
import cg.usermanagement.gwt.shared.data.UserListData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelOperation;
import cg.usermanagement.gwt.shared.data.UserRegisterData;
import cg.usermanagement.model.view.PermissionView;
import cg.usermanagement.model.view.RoleView;
import cg.usermanagement.model.view.UserRegisterView;
import cg.usermanagement.model.view.UserSearchView;
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
  
  @Override
  public FrameData getStartUI( String localeName )
  {
    FrameData frameRd = ResponseUtil.buildStartUI( LocaleUtil.getLocale( localeName ) );
    
    //put into the session
    String sessionId = SessionManager.startSession();
    setSessionId( sessionId );
    
    ResponseUtil.updateResponseDatasToSession( frameRd );
    return frameRd;
  }
  
//  public ResponseData< ControlSectionData > buildControlSectionData( Locale locale )
//  {
//    return buildControlSectionData( locale );
//  }


  /*
   * when the locale changed, the server should response with resource data of new locale and refresh the page
   * @see cg.usermanagement.gwt.client.IUserManagement#changeLocale(java.lang.String)
   */
  @Override
  @SuppressWarnings( "unchecked" )
  public FrameData changeLocale( String localeName )
  {
    Locale locale = LocaleUtil.getLocale( localeName );
    adjustSessionManager();
    SessionManager.putAttribute( UserManagementSessionKey.locale, locale );

    FrameData frameData = (FrameData)SessionManager.getAttribute( UserManagementSessionKey.currentFrameData );
    List< ResponseData<?> > rds = frameData.getResponseDatas();
    if( rds == null || rds.size() == 0 )
      return frameData;
    for( ResponseData<?> rd : rds )
    {
      if( rd.getContentData() != null )
        ResourceDataManager.defaultInstance.injectResourceDatas( locale, rd.getContentData(), false );
      
      //handle locale menu specially
      if( UIIdentity.CONTROL_SECTION.equals( rd.getFlowData().getUiIdentity() ) )
        ResponseUtil.fillLocaleMenuItems( ( (ControlSectionData)rd.getContentData() ).getMenuPanelData().get( 0 ) );
    }
    return frameData;
  }
  

  
  
  public FrameData userlogin( String userName, String password ) throws LoginException
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
    
    FrameData frameData = ResponseUtil.getUserManagementPanelDatas( getCurrentLocale() );
    ResponseUtil.updateResponseDatasToSession( frameData );
    
    return frameData;
  }

  //it should be account instead of user login
  @Override
  public FrameData accountlogin( String accountName, String password ) throws LoginException
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
    
    FrameData frameData = ResponseUtil.getUserManagementPanelDatas( getCurrentLocale() );
    ResponseUtil.updateResponseDatasToSession( frameData );
    
    return frameData;

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
  public FrameData onUserManagementPanelOperation( UserManagementPanelOperation operation )
  {
    adjustSessionManager();
    List< ResponseData<?> > rds = new ArrayList< ResponseData<?> >();

    if( UserManagementPanelOperation.SearchUser.equals( operation ) )
    {
      ResponseData< SearchUserData > rd = new ResponseData< SearchUserData >();
      rd.setFlowData( UIIdentity.UM_SEARCH_USER );

      SearchUserData data = new SearchUserData();
      ResourceDataManager.defaultInstance.injectResourceDatas( getCurrentLocale(), data, true );
      rd.setContentData( data );
      
      rds.add( rd );
      ResponseUtil.updateClientSectionResponseDataToSession( rd );
      return new FrameData( Frame.UMF_SEARCH_USER, rds );

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
      ResponseData< RoleDetailData > rd = new ResponseData< RoleDetailData >();
      rd.setFlowData( UIIdentity.UM_ADD_ROLE );

      RoleDetailData data = new RoleDetailData();
      ResourceDataManager.defaultInstance.injectResourceDatas( getCurrentLocale(), data, true );
      rd.setContentData( data );
      
      rds.add( rd );
      ResponseUtil.updateClientSectionResponseDataToSession( rd );
      return new FrameData( Frame.UMF_ADD_ROLE, rds );
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
  
  public FrameData searchUser( SearchUserData searchUserData )
  {
    adjustSessionManager();
    
    UserSearchCriteria criteria = new UserSearchCriteria();
    EntityUtil.shallowCopyEntity( searchUserData, criteria );
    IUserService service = getUserService();
    
    List< UserSearchView > userSearchViews = service.findUsers( criteria );

    List< UserListData > userDatas = new ArrayList< UserListData >();
    for( UserSearchView userSearchView : userSearchViews )
    {
      UserListData userListData = new UserListData();
      EntityUtil.shallowCopyEntity( userSearchView, userListData );
      userDatas.add( userListData );
    }
    
    FrameData frameData = UserManagementResponseUtil.getListUsersResponses( getCurrentLocale(), userDatas );
    ResponseUtil.updateResponseDatasToSession( frameData );
    
    return frameData;
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
