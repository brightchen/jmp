package cg.usermanagement.gwt.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cg.gwt.components.shared.data.MenuBarData;
import cg.gwt.components.shared.data.ResponseData;
import cg.gwt.components.shared.data.UIIdentity;
import cg.resourcemanagement.util.LocaleUtil;
import cg.services.session.SessionManager;
import cg.usermanagement.api.IUserService;
import cg.usermanagement.gwt.client.IUserManagement;
import cg.usermanagement.gwt.server.resource.UserManagementResourceDataBuilder;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.ControlSectionData;
import cg.usermanagement.gwt.shared.data.LocaleMenuItemData;
import cg.usermanagement.gwt.shared.data.SearchUserData;
import cg.usermanagement.gwt.shared.data.UserListData;
import cg.usermanagement.gwt.shared.data.UserLoginData;
import cg.usermanagement.gwt.shared.data.UserManagementData;
import cg.usermanagement.gwt.shared.data.UserRegisterData;
import cg.usermanagement.model.view.PermissionView;
import cg.usermanagement.model.view.RoleView;
import cg.usermanagement.model.view.UserRegisterView;
import cg.usermanagement.shared.LoginException;
import cg.usermanagement.shared.RegisterUserException;
import cg.usermanagement.shared.RoleException;
import cg.utils.DataConverter;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserManagementServlet extends RemoteServiceServlet implements IUserManagement
{
  private static final long serialVersionUID = -8926280156981738193L;
  
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

  
  public List< ResponseData<?> > getStartUI( String localeName )
  {
    List< ResponseData<?> > rds = new ArrayList< ResponseData<?> >();
    
    // control section data
    {
      ResponseData< ControlSectionData > rd = new ResponseData< ControlSectionData >();
      rd.setFlowData( UIIdentity.CONTROL_SECTION );
      
      MenuBarData menuBarData = new MenuBarData(); 
      menuBarData.setTitle( "Locale" );
      Map< String, String > localeDatas = UserManagementResourceDataBuilder.getSupportedLocalesData();
      for( Map.Entry< String, String > localeData : localeDatas.entrySet() )
      {
        //use locale name as the command key and locale name's resource value as menu item's title
        LocaleMenuItemData menuItemData = new LocaleMenuItemData( localeData.getValue(), localeData.getKey() );
        menuBarData.addMenuItemData( menuItemData );
      }
      ControlSectionData controlSectionData = new ControlSectionData();
      controlSectionData.addMenuBarData( menuBarData );
      rd.setContentData( controlSectionData );
      
      rds.add( rd );
    }
    
    // user management data
    {
      Locale locale = LocaleUtil.getLocale( localeName );
      if( locale == null )
        locale = LocaleUtil.TOP_LOCALE;
      ResponseData< UserManagementData > rd = new ResponseData< UserManagementData >();
      rd.setFlowData( UIIdentity.UM_START );
      UserLoginData userLoginData = new UserLoginData();
      userLoginData.setResourceData( UserManagementResourceDataBuilder.buildUserLoginResourceData( locale ) );
      
      AccountLoginData accountLoginData = new AccountLoginData();
      accountLoginData.setResourceData( UserManagementResourceDataBuilder.buildAccountLoginResourceData( locale ) );
      
      UserRegisterData userRegisterData = new UserRegisterData();
      //set resource data later
      //userRegisterData.setResourceData( UserManagementResourceDataBuilder.b() );
      
      UserManagementData data = new UserManagementData( userLoginData, accountLoginData, userRegisterData );
      rd.setContentData( data );
      
      rds.add( rd );
    }    

    
    //put into the session
    SessionManager.startSession();
    SessionManager.putAttribute( UserManagementSessionKey.currentPageDatas, rds );

    return rds;
  }

  /*
   * when the locale changed, the server should response with resource data of new locale and refresh the page
   * @see cg.usermanagement.gwt.client.IUserManagement#changeLocale(java.lang.String)
   */
  @Override
  public List< ResponseData<?> > changeLocale( String localeName )
  {
    Locale locale = LocaleUtil.getLocale( localeName );
    SessionManager.putAttribute( UserManagementSessionKey.locale, locale );

    List< ResponseData<?> > rds = (List< ResponseData<?> >)SessionManager.getAttribute( UserManagementSessionKey.currentPageDatas );
    if( rds == null || rds.size() == 0 )
      return rds;
    for( ResponseData<?> rd : rds )
    {
      rd.getContentData().getResourceData()
    }
  }
  
  public void userlogin( String userName, String password ) throws LoginException
  {
    log.debug( "login: user=" + userName + "; password=" + password );
    if( password == null || password.isEmpty() )
      throw new LoginException( LoginException.LOGIN_ERROR.PASSWORD_EMTPY );
    
    IUserService service = getUserService();
    Set< PermissionView > permissions = service.userLogin( userName, password );

    SessionManager.startSession();
    SessionManager.putAttribute( UserManagementSessionKey.userName, userName );

    // cache the user permissions in the session as it is a very frequently used 
    SessionManager.putAttribute( UserManagementSessionKey.userPermissions, permissions );
  }

  //it should be account instead of user login
  @Override
  public void accountlogin( String accountName, String password ) throws LoginException
  {
    log.debug( "login: account=" + accountName + "; password=" + password );
    if( password == null || password.isEmpty() )
      throw new LoginException( LoginException.LOGIN_ERROR.PASSWORD_EMTPY );
    
    IUserService service = getUserService();
    Set< PermissionView > permissions = service.accountLogin( accountName, password );

    SessionManager.startSession();
    SessionManager.putAttribute( UserManagementSessionKey.accountName, accountName );
    
    // cache the role permissions in the session as it is a very frequently used 
    SessionManager.putAttribute( UserManagementSessionKey.accountPermissions, permissions );
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
}
