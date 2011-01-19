package cg.config;

import java.util.Properties;

public class Configurator
{
  private static Configurator instance;
  
  protected IConfigMergeStrategy configStrategy;
  
  private Configurator(){};
  
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

  
  protected IConfigMergeStrategy getConfigStrategy()
  {
    if( configStrategy == null )
    {
      configStrategy = new ConfigMergeTypicalStrategy();
    }
    return configStrategy;
  }

  public void setConfigStrategy( IConfigMergeStrategy configStrategy )
  {
    this.configStrategy = configStrategy;
  }
  
  
}
