package cg.dbmanagement.gwt.client;

import java.util.List;
import java.util.Map;

import cg.dbmanagement.gwt.shared.data.DbException;
import cg.dbmanagement.gwt.shared.data.DbUserLoginData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath( "persistenceService" )
public interface IPersistenceService extends RemoteService
{
  public String connectToDb( DbUserLoginData dbData ) throws DbException;
  public Map< String, List< Object > > executeNativeQuery( String persistenceSessionId, String sql ) throws DbException;
}
