package cg.usermanagement.gwt.server;

import cg.service.lookup.ServiceManager;
import cg.service.lookup.ServiceNotFoundException;
import cg.usermanagement.api.model.IAccount;
import cg.usermanagement.api.service.IUserService;
import cg.usermanagement.gwt.client.IAuthenticateService;
import cg.usermanagement.gwt.shared.LoginException;
import cg.usermanagement.gwt.shared.RegisterUserException;
import cg.usermanagement.gwt.shared.data.UserRegisterData;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AuthenticateServlet extends RemoteServiceServlet implements IAuthenticateService
{
  private static final long serialVersionUID = -8926280156981738193L;

  //it should be account instead of user login
  @Override
  public void login( String accountId, String password ) throws LoginException
  {
    if( password == null || password.isEmpty() )
      throw new LoginException( LoginException.LOGIN_ERROR.PASSWORD_EMTPY );
    
    IUserService service = null;
    try
    {
      service = ServiceManager.findService( IUserService.class );
    }
    catch( ServiceNotFoundException snfe )
    {
      throw new RuntimeException( "can't find service: " + IUserService.class.getName(), snfe );
    }
   
    IAccount account = service.findAccountByAccountId( accountId );
    if( account == null )
      throw new LoginException( LoginException.LOGIN_ERROR.INVALID_ACCOUNT_ID );
    if( !password.equals( account.getPassword() ) )
      throw new LoginException( LoginException.LOGIN_ERROR.ACCOUNT_PASSWORD_NOT_MATCH );
  }
  
  public void registerUser( UserRegisterData data ) throws RegisterUserException
  {
    //TODO
  }
}
