package cg.dbmanagement.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IConfigServiceAsync
{
  public void getSupportedDatabases( AsyncCallback< String[] > callback );
}
