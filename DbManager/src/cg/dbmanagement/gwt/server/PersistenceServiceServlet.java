package cg.dbmanagement.gwt.server;

import java.util.List;
import java.util.Map;

import cg.dbmanagement.config.DatabaseType;
import cg.dbmanagement.gwt.client.IPersistenceService;
import cg.dbmanagement.gwt.shared.data.DbException;
import cg.dbmanagement.gwt.shared.data.DbUserLoginData;
import cg.gwt.services.client.ISessionManagementService;
import cg.gwt.services.client.ISessionManagementServiceAsync;
import cg.persistence.api.PersistenceException;
import cg.persistence.jdbc.ConnectionParameters;
import cg.persistence.jdbc.PersistenceManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PersistenceServiceServlet extends RemoteServiceServlet implements IPersistenceService
{
  private static final long serialVersionUID = -5623889683040654632L;

  //return the identity of the session.
  //use String instead of long as it is generic type for transmit information between client and server
  @Override
  public String connectToDb( DbUserLoginData dbData ) throws DbException
  {
    String databaseName = dbData.getSupportedDatabases()[ dbData.getSelectedDatabaseIndex() ];
    String driverClass = DatabaseType.getDriverClass( databaseName );
    
    ConnectionParameters connParams = new ConnectionParameters();
    connParams.setDriverClass( driverClass );
    connParams.setUrl( dbData.getJdbcUrl() );
    connParams.setUserName( dbData.getUserName() );
    connParams.setPassword( dbData.getPassword() );
    
    try
    {
      return PersistenceManager.connect( connParams );
    }
    catch( PersistenceException e )
    {
      throw new DbException( "connect to database failed: " + e.getMessage() );
    }
  }

  @Override
  public Map< String, List< Object > > executeNativeQuery( String persistenceSessionId, String sql ) throws DbException
  {
    try
    {
      return PersistenceManager.executeNativeQuery( persistenceSessionId, sql );
    }
    catch( PersistenceException e )
    {
      throw new DbException( "execute sql failed: " + e.getMessage() );
    }

  }
}
