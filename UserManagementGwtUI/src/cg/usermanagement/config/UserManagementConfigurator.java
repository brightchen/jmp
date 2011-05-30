package cg.usermanagement.config;

import java.util.HashSet;
import java.util.Set;

import cg.config.Configurator;
import cg.usermanagement.config.PropertyKeys.PropertyKey;

public class UserManagementConfigurator extends Configurator
{
  private static UserManagementConfigurator   instance;

  protected String database;
  
  protected Set< String > databaseDependedPropertyKeys = new HashSet< String >();
  protected UserManagementConfigurator()
  {
  }
  
  protected void init()
  {
    databaseDependedPropertyKeys.add( PropertyKeys.getPropertyStringKey( PropertyKey.db$jdbc$driver_class ) );
    databaseDependedPropertyKeys.add( PropertyKeys.getPropertyStringKey( PropertyKey.db$jdbc$url ) );
    databaseDependedPropertyKeys.add( PropertyKeys.getPropertyStringKey( PropertyKey.db$jpa$type ) );
  }

  public static UserManagementConfigurator getInstance()
  {
    if ( instance == null )
    {
      synchronized ( UserManagementConfigurator.class )
      {
        if ( instance == null )
        {
          instance = new UserManagementConfigurator();
          instance.init();
        }
      }
    }

    return instance;
  }
  
  /*
   * the database related property value is depended on the selected database
   * for example, the db$jdbc$driver_class should be set to the mysql$jdbc$driver_class if database is mysql;
   * @see cg.config.Configurator#getProperty(java.lang.String)
   */
  @Override
  public String getProperty( String name )
  {
    if( isDatabaseDependedProperty( name ) )
    {
      String newName = getDatabaseSpecificPropertyName( name );
      return getProperty( newName );
    }
    return super.getProperty( name );
  }
  
  protected String getDatabase()
  {
    if( database == null )
    {
      database = getProperty( "db.database" );
    }
    return database;
  }
  
  protected boolean isDatabaseDependedProperty( String name )
  {
    return databaseDependedPropertyKeys.contains( name );
  }
  
  protected String getDatabaseSpecificPropertyName( String name )
  {
    String theDatabase = getDatabase();
    int index = name.indexOf( "." );
    //index must great than 0
    String prefix = name.substring( 0, index );
    
    return name.replaceFirst( prefix, theDatabase );
  }
}
