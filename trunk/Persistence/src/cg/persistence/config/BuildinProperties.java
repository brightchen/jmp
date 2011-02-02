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
    props.setProperty( "db.jdbc.driver_class", "org.apache.derby.jdbc.EmbeddedDriver" );
    props.setProperty( "db.jdbc.url", "jdbc:derby:testdb;create=true" );
    props.setProperty( "db.username", "user1" );
    props.setProperty( "db.password", "user1" );
    props.setProperty( "db.validation.query", "" );

    return props;
  }
}
