package cg.usermanagement.gwt.server;

import java.util.Set;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cg.services.session.SessionManager;
import cg.usermanagement.api.IUserService;
import cg.usermanagement.gwt.client.IUserManagement;
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
  public void addRole( String roleName ) throws RoleException
  {
    IUserService service = getUserService();
    RoleView roleView = service.addRole( roleName );
  }
}
