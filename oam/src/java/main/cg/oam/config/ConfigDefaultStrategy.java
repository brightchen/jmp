package cg.oam.config;

import java.util.Properties;

// the default strategy is runtime input properties > default properties > buildin properties
public class ConfigDefaultStrategy implements ConfigStrategy
{
  private Properties props;
  
  @Override
  public String getProperty( String name )
  {
    if( props == null )
      props = getProperties();
    return props.getProperty( name );
  }

  @Override
  public Properties getProperties()
  {
    if( props != null )
      return props;
    props = ConfigUtil.merge( BuildinProperties.getBuildinProperties(), DefaultProperties.getDefaultProperties() );
    
    //merge all system properties
    props = ConfigUtil.merge( props, System.getProperties() );
    
    return props;
  }

}
