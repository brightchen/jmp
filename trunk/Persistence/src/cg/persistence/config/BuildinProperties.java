package cg.persistence.config;

import java.util.Properties;

public class BuildinProperties
{
  private static Properties props;
  
  public static Properties getProperties()
  {
    if( props != null )
      return props;
    
    props = new Properties();
    props.put( "db.jdbc.driver_class", "org.apache.derby.jdbc.EmbeddedDriver" );
    props.put( "db.jdbc.url", "jdbc:derby:test;create=true" );
    props.put( "db.username", "user1" );
    props.put( "db.password", "user1" );
    props.put( "db.validation.query", "" );

    return props;
  }
}
