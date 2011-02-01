package cg.dbmanagement.gwt.server;

import cg.dbmanagement.config.DbManagerConfigurator;
import cg.dbmanagement.gwt.client.IConfigService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ConfigServiceServlet extends RemoteServiceServlet implements IConfigService
{
  private static final long serialVersionUID = -2585965890496248229L;

  @Override
  public String[] getSupportedDatabases()
  {
    DbManagerConfigurator configurator = DbManagerConfigurator.getInstance();
    return configurator.getSupportedDatabases();
  }

}
