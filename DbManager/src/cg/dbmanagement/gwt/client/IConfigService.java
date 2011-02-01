package cg.dbmanagement.gwt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath( "configService")
public interface IConfigService extends RemoteService
{
  public String[] getSupportedDatabases();
}
