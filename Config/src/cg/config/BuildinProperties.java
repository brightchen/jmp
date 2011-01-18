package cg.config;

import java.util.Properties;

public class BuildinProperties
{
  public static enum SECTION
  {
    MAIL,
    DB_DERBY
  }
  
  public static Properties getProperties( SECTION[] sections )
  {
    if( sections == null )
      return null;

    MergeableProperties props = new MergeableProperties();

    for( SECTION section : sections )
    {
      props.merge( getProperties( section ) ); 
    }
    return props;
  }
  
  public static Properties getProperties( SECTION section )
  {
    Properties props = new Properties();
    if( SECTION.MAIL.equals( section ) )
    {
      return props;
    }
    if( SECTION.DB_DERBY.equals( section ) )
    {
      props.put( "db.jdbc.driver_class", "org.apache.derby.jdbc.EmbeddedDriver" );
      props.put( "db.jdbc.url", "jdbc:derby:test;create=true" );
      props.put( "db.username", "user1" );
      props.put( "db.password", "user1" );
      props.put( "db.validation.query", "" );
      return props;
    }
    
    return props;
  }
}
