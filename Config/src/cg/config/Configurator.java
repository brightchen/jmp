package cg.config;

import java.util.Properties;

public class Configurator
{
  private static Configurator instance;
  
  protected IConfigMergeStrategy configStrategy;
  
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
  
  protected IConfigMergeStrategy getConfigStrategy()
  {
    if( configStrategy == null )
    {
      ConfigMergeTypicalStrategy strategy = new ConfigMergeTypicalStrategy();
      strategy.setBuildinProperties( getBuildinProperties() );
      configStrategy = strategy;
    }
    return configStrategy;
  }

  public void setConfigStrategy( IConfigMergeStrategy configStrategy )
  {
    this.configStrategy = configStrategy;
  }
  
  protected Properties getBuildinProperties()
  {
    return new Properties();
  }
  
}
