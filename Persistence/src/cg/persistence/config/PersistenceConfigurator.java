package cg.persistence.config;

import java.util.Properties;

import cg.config.Configurator;
import cg.persistence.config.PersistencePropertyName.PROPERTY_NAME;

public class PersistenceConfigurator extends Configurator
{
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

  public String getProperty( PROPERTY_NAME property )
  {
    return getProperties().getProperty( property.getPropertyKey() );
  }
  
  protected Properties getBuildinProperties()
  {
    return BuildinProperties.getProperties();
  }

  
}
