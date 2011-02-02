package cg.usermanagement.gwt.server;

import cg.usermanagement.api.model.IUser;
import cg.usermanagement.api.service.IUserService;
import cg.usermanagement.gwt.client.IAuthenticateService;
import cg.usermanagement.gwt.shared.LoginException;
import cg.usermanagement.persistence.UserService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AuthenticateServlet extends RemoteServiceServlet implements IAuthenticateService
{
  private static final long serialVersionUID = -8926280156981738193L;

  @Override
  public void login( String userName, String password ) throws LoginException
  {
    IUserService service = new UserService();
    IUser user = service.findUserByName( userName );
    if( user == null || password == null || !password.equals( user.getPassword() ) )
      throw new LoginException( LoginException.LOGIN_ERROR.USERNAME_PASSWORD_NOT_MATCH );
  }
}
