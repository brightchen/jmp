package cg.usermanagement.gwt.client;

import cg.usermanagement.gwt.shared.LoginException;
import cg.usermanagement.gwt.shared.RegisterUserException;
import cg.usermanagement.gwt.shared.data.UserRegisterData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath( "authenticate")
public interface IAuthenticateService extends RemoteService
{
  public void login( String accountId, String password ) throws LoginException;
  
  public void registerUser( UserRegisterData data ) throws RegisterUserException;
}
