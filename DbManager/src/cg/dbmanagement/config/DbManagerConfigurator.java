package cg.dbmanagement.config;

import java.util.Properties;

import cg.config.Configurator;

public class DbManagerConfigurator extends Configurator
{
  
  private static DbManagerConfigurator instance;
  
  protected DbManagerConfigurator(){};
  
  public static DbManagerConfigurator getInstance()
  {
    if( instance == null )
    {
      synchronized( DbManagerConfigurator.class )
      {
        if( instance == null )
        {
          instance = new DbManagerConfigurator();
        }
      }
    }
    
    return instance;
  }

  public String getProperty( DbManagerPropertyName.PROPERTY_NAME propertyName )
  {
    return getProperty( propertyName.getPropertyKey() );
  }

  public String[] getSupportedDatabases()
  {
    String databases = getProperty( DbManagerPropertyName.PROPERTY_NAME.databases );
    if( databases == null || databases.isEmpty() )
      return null;
    return databases.split( DbManagerBuildinProperties.seperator );
  }
  
  @Override
  protected Properties getBuildinProperties()
  {
    return DbManagerBuildinProperties.getProperties();
  }

}
