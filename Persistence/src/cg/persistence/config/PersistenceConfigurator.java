package cg.persistence.config;

import java.util.Properties;

import cg.config.Configurator;

public class PersistenceConfigurator extends Configurator
{
  public static enum PROPERTY_ENUM
  {
    DRIVER_CLASS( "db.jdbc.driver_class" ),
    URL( "db.jdbc.url" ),
    USERNAME( "db.username" ),
    PASSWORD( "db.password" ),
    VALIDATION_QUERY( "db.validation.query" );

    private String property;
    private PROPERTY_ENUM( String property )
    {
      this.property = property;
    }
  }
  private static PersistenceConfigurator instance;
  
  protected PersistenceConfigurator(){};
  
  public static PersistenceConfigurator getInstance()
  {
    if( instance == null )
    {
      synchronized( PersistenceConfigurator.class )
      {
        if( instance == null )
        {
          instance = new PersistenceConfigurator();
        }
      }
    }
    
    return instance;
  }

  public String getProperty( PROPERTY_ENUM property )
  {
    return getProperties().getProperty( property.property );
  }
  
  protected Properties getBuildinProperties()
  {
    return BuildinProperties.getProperties();
  }

  
}
