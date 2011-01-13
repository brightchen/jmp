package cg.gwt.user.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cg.gwt.user.shared.LoginException;

@RemoteServiceRelativePath( "user")
public interface UserService extends RemoteService
{
  public void login( String userName, String password ) throws LoginException;
}
