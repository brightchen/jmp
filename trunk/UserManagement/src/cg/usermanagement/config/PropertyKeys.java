package cg.usermanagement.config;


public class PropertyKeys
{
  public static enum PropertyKey
  {
    db$database,
    db$username,
    db$password,
    db$validation$query,
    db$jdbc$driver_class,
    db$jdbc$url,
    db$jpa$type,

    mysql$jdbc$driver_class,
    mysql$jdbc$url,
    mysql$jpa$type,

    derby$jdbc$driver_class,
    derby$jdbc$url,
    derby$jpa$type
  }
  
  public static String getPropertyStringKey( PropertyKey propertyKey )
  {
    return propertyKey.name().replace( '$', '.' );
  }
  
//  props.setProperty( "db.database", "mysql" );  //derby
//  
//  props.setProperty( "db.username", "user1" );
//  props.setProperty( "db.password", "user1" );
//  props.setProperty( "db.validation.query", "" );
//
//  //mysql
//  props.setProperty( "mysql.jdbc.driver_class", "com.mysql.jdbc.Driver" );
//  props.setProperty( "mysql.jdbc.url", "jdbc:mysql://localhost:3306/userdb" );
//  props.setProperty( "mysql.validation.query", "" );
//  props.setProperty( "mysql.jpa.type", "MYSQL" );
//  
//  //derby
//  props.setProperty( "derby.jdbc.driver_class", "org.apache.derby.jdbc.EmbeddedDriver" );
//  props.setProperty( "derby.jdbc.url", "jdbc:derby:test;create=true" );
//  props.setProperty( "derby.validation.query", "" );
//  props.setProperty( "derby.jpa.type", "DEFAULT" );

}
