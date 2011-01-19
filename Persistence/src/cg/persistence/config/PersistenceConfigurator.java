package cg.persistence.config;

import java.util.Properties;

import cg.config.Configurator;

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

  
  protected Properties getBuildinProperties()
  {
    return BuildinProperties.getProperties();
  }

}
