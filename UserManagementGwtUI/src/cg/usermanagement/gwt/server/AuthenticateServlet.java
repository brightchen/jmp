package cg.usermanagement.gwt.server;

import cg.usermanagement.gwt.client.IAuthenticateService;
import cg.usermanagement.gwt.shared.LoginException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AuthenticateServlet extends RemoteServiceServlet implements IAuthenticateService
{
  private static final long serialVersionUID = -8926280156981738193L;

  @Override
  public void login( String userName, String password ) throws LoginException
  {
    throw new LoginException( LoginException.LOGIN_ERROR.USERNAME_PASSWORD_NOT_MATCH );
  }
}
