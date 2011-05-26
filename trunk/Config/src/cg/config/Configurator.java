package cg.config;

import java.util.Properties;

public class Configurator
{
  private static Configurator instance;
  
  protected IPropertiesStrategy configStrategy;
  protected IPropertiesStrategy buildinPropertiesStrategy;
  
  protected Configurator(){};
  
  public static Configurator getInstance()
  {
    if( instance == null )
    {
      synchronized( Configurator.class )
      {
        if( instance == null )
        {
          instance = new Configurator();
        }
      }
    }
    
    return instance;
  }
  
  public Properties getProperties()
  {
    return getConfigStrategy().getProperties();
  }

  public String getProperty( String name )
  {
    return getProperties().getProperty( name );
  }
  
  protected IPropertiesStrategy getConfigStrategy()
  {
    if( configStrategy == null )
    {
      ConfigMergeTypicalStrategy strategy = new ConfigMergeTypicalStrategy();
      strategy.setBuildinProperties( getBuildinProperties() );
      configStrategy = strategy;
    }
    return configStrategy;
  }

  public void setConfigStrategy( IPropertiesStrategy configStrategy )
  {
    this.configStrategy = configStrategy;
  }
  
  protected Properties getBuildinProperties()
  {
    return getBuildinPropertiesStrategy().getProperties();
  }
  
  protected IPropertiesStrategy getBuildinPropertiesStrategy()
  {
    if( buildinPropertiesStrategy == null )
      buildinPropertiesStrategy = new BuildinPropertiesTypicalLookupStrategy();
    return buildinPropertiesStrategy;
  }

  public void setBuildinPropertiesStrategy( IPropertiesStrategy buildinPropertiesStrategy )
  {
    this.buildinPropertiesStrategy = buildinPropertiesStrategy;
  }
  
}
