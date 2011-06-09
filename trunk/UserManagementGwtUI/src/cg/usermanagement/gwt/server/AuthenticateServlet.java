package cg.usermanagement.gwt.server;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cg.usermanagement.api.IUserService;
import cg.usermanagement.gwt.client.IAuthenticateService;
import cg.usermanagement.gwt.shared.data.UserRegisterData;
import cg.usermanagement.model.Account;
import cg.usermanagement.model.User;
import cg.usermanagement.model.view.UserRegisterView;
import cg.usermanagement.shared.LoginException;
import cg.usermanagement.shared.RegisterUserException;
import cg.utils.DataConverter;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AuthenticateServlet extends RemoteServiceServlet implements IAuthenticateService
{
  private static final long serialVersionUID = -8926280156981738193L;
  
  private Logger log = Logger.getLogger( AuthenticateServlet.class );
  
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
    User user = service.findUserByName( userName );
    if( user == null )
      throw new LoginException( LoginException.LOGIN_ERROR.INVALID_ACCOUNT );
    if( !password.equals( user.getPassword() ) )
      throw new LoginException( LoginException.LOGIN_ERROR.ACCOUNT_PASSWORD_NOT_MATCH );
  }

  //it should be account instead of user login
  @Override
  public void accountlogin( String accountName, String password ) throws LoginException
  {
    log.debug( "login: account=" + accountName + "; password=" + password );
    if( password == null || password.isEmpty() )
      throw new LoginException( LoginException.LOGIN_ERROR.PASSWORD_EMTPY );
    
    IUserService service = getUserService();
    Account account = service.findAccountByName( accountName );
    if( account == null )
      throw new LoginException( LoginException.LOGIN_ERROR.INVALID_ACCOUNT );
    if( !password.equals( account.getPassword() ) )
      throw new LoginException( LoginException.LOGIN_ERROR.ACCOUNT_PASSWORD_NOT_MATCH );
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
}
