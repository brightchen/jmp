package cg.dbmanagement.gwt.client;

import java.util.List;
import java.util.Map;

import cg.dbmanagement.gwt.shared.data.DbUserLoginData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IPersistenceServiceAsync
{
  public void connectToDb( DbUserLoginData dbData, AsyncCallback< String > callback );
  public void executeNativeQuery( String persistenceSessionId, String sql, AsyncCallback< Map< String, List< Object > > > callback );
}
