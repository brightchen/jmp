package cg.usermanagement.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IAuthenticateServiceAsync
{
  //the async version should not throw any exception
  public void login( String userName, String password, AsyncCallback< Void > callback  );
}
