package cg.dbmanagement.gwt.server;

import java.sql.Connection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cg.dbmanagement.config.DatabaseType;
import cg.dbmanagement.gwt.client.IPersistenceService;
import cg.dbmanagement.gwt.shared.data.DbException;
import cg.dbmanagement.gwt.shared.data.DbUserLoginData;
import cg.persistence.jdbc.ConnectionManager;
import cg.persistence.jdbc.ConnectionManagerFactory;
import cg.persistence.jdbc.ConnectionParameters;

public class PersistenceServiceServlet extends RemoteServiceServlet implements IPersistenceService
{
  private static final long serialVersionUID = -5623889683040654632L;

  @Override
  public void connectToDb( DbUserLoginData dbData ) throws DbException
  {
    String databaseName = dbData.getSupportedDatabases()[ dbData.getSelectedDatabaseIndex() ];
    String driverClass = DatabaseType.getDriverClass( databaseName );
    
    ConnectionParameters connParams = new ConnectionParameters();
    connParams.setDriverClass( driverClass );
    connParams.setUrl( dbData.getJdbcUrl() );
    connParams.setUserName( dbData.getUserName() );
    connParams.setPassword( dbData.getPassword() );
    
    ConnectionManager connManager = ConnectionManagerFactory.getConnectionManager( connParams );
    Connection connection = connManager.getConnectionForUse();
    if( connection == null )
      throw new DbException( "get connection failed" );
    
  }

}
