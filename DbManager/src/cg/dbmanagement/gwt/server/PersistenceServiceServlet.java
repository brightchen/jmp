package cg.dbmanagement.gwt.server;

import cg.dbmanagement.config.DatabaseType;
import cg.dbmanagement.gwt.client.IPersistenceService;
import cg.dbmanagement.gwt.shared.data.DbException;
import cg.dbmanagement.gwt.shared.data.DbUserLoginData;
import cg.persistence.api.PersistenceException;
import cg.persistence.jdbc.ConnectionParameters;
import cg.persistence.jdbc.PersistenceSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

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
    
    PersistenceSession session = new PersistenceSession( connParams );
    try
    {
      session.connect();
    }
    catch( PersistenceException e )
    {
      throw new DbException( "connect to database failed: " + e.getMessage() );
    }
  }

}
