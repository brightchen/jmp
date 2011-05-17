package cg.usermanagement.gwt.client;

import cg.usermanagement.gwt.shared.data.UserRegisterData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IAuthenticateServiceAsync
{
  //the async version should not throw any exception
  public void login( String accountId, String password, AsyncCallback< Void > callback  );
  
  public void registerUser( UserRegisterData data, AsyncCallback< Void > callback );
}
