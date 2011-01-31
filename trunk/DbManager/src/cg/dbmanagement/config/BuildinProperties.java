package cg.dbmanagement.config;

import java.util.Properties;

public class BuildinProperties
{
  public static String seperator = ":";

  private static Properties props;
  private static String driverClassPrefix = "db.jdbc.driver_class";
  
  public static Properties getProperties()
  {
    if( props != null )
      return props;
    
    props = new Properties();
    StringBuilder databases = new StringBuilder();
    for( DatabaseType.TYPE type : DatabaseType.TYPE.values() )
    {
      databases.append( type.name() ).append( seperator );
      props.put( driverClassPrefix + "." + type.name(), DatabaseType.getDriverClass( type ) );
    }
    props.put( "databases", databases );
    
    return props;
  }

}
