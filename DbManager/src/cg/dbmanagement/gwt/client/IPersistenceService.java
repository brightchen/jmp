package cg.dbmanagement.gwt.client;

import cg.dbmanagement.gwt.shared.data.DbException;
import cg.dbmanagement.gwt.shared.data.DbUserLoginData;
import cg.dbmanagement.gwt.shared.data.SqlOutputData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath( "persistenceService" )
public interface IPersistenceService extends RemoteService
{
  public String connectToDb( DbUserLoginData dbData ) throws DbException;
  public SqlOutputData executeNativeSql( String persistenceSessionId, String sql ) throws DbException;
}
