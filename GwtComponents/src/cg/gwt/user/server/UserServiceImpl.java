package cg.gwt.user.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cg.gwt.user.client.UserService;
import cg.gwt.user.shared.LoginException;

public class UserServiceImpl extends RemoteServiceServlet implements UserService
{
  private static final long serialVersionUID = -8926280156981738193L;

  @Override
  public void login( String userName, String password ) throws LoginException
  {
    throw new LoginException( LoginException.LOGIN_ERROR.USERNAME_PASSWORD_NOT_MATCH );
  }

}
