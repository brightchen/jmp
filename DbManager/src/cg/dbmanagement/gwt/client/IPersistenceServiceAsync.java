package cg.dbmanagement.gwt.client;

import cg.dbmanagement.gwt.shared.data.DbUserLoginData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IPersistenceServiceAsync
{
  public void connectToDb( DbUserLoginData dbData, AsyncCallback< Void > callback );
}
