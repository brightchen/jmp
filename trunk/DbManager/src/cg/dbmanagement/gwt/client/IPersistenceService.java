package cg.dbmanagement.gwt.client;

import cg.dbmanagement.gwt.shared.data.DbException;
import cg.dbmanagement.gwt.shared.data.DbUserLoginData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath( "persistenceService")
public interface IPersistenceService extends RemoteService
{
  public void connectToDb( DbUserLoginData dbData ) throws DbException;
}
