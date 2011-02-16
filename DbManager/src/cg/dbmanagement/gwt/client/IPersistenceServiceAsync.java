package cg.dbmanagement.gwt.client;

import cg.dbmanagement.gwt.shared.data.DbUserLoginData;
import cg.dbmanagement.gwt.shared.data.SqlOutputData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IPersistenceServiceAsync
{
  public void connectToDb( DbUserLoginData dbData, AsyncCallback< String > callback );
  public void executeNativeSql( String persistenceSessionId, String sql, AsyncCallback< SqlOutputData > callback );
}
