package cg.usermanagement.gwt.client;

import cg.usermanagement.gwt.shared.data.UserRegisterData;
import cg.usermanagement.shared.LoginException;
import cg.usermanagement.shared.RegisterUserException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath( "authenticate")
public interface IAuthenticateService extends RemoteService
{
  public void userlogin( String userName, String password ) throws LoginException;
  public void accountlogin( String accountName, String password ) throws LoginException;
  
  public void registerUser( UserRegisterData data ) throws RegisterUserException;
}
