package cg.usermanagement.config;

import java.util.Properties;

import cg.config.IBuildinProperties;
import cg.usermanagement.config.PropertyKeys.PropertyKey;

public class UserManagementBuildinProperties implements IBuildinProperties
{
  private Properties props;
  
  @Override
  public Properties getProperties()
  {
    if( props != null )
      return props;
    
    props = new Properties();
    setProperty( props, PropertyKey.db$database, "mysql" );  //derby

    setProperty( props, PropertyKey.db$username, "user1" );
    setProperty( props, PropertyKey.db$password, "user1" );
    setProperty( props, PropertyKey.db$validation$query, "" );

    //mysql
    setProperty( props, PropertyKey.mysql$jdbc$driver_class, "com.mysql.jdbc.Driver" );
    setProperty( props, PropertyKey.mysql$jdbc$url, "jdbc:mysql://localhost:3306/userdb" );
    setProperty( props, PropertyKey.mysql$jpa$type, "MYSQL" );
    
    //derby
    setProperty( props, PropertyKey.derby$jdbc$driver_class, "org.apache.derby.jdbc.EmbeddedDriver" );
    setProperty( props, PropertyKey.derby$jdbc$url, "jdbc:derby:test;create=true" );
    setProperty( props, PropertyKey.derby$jpa$type, "DEFAULT" );

    return props;
  }
  
  public void setProperty( Properties props, PropertyKey propertyKey, String propertyValue )
  {
    props.setProperty( PropertyKeys.getPropertyStringKey( propertyKey ), propertyValue );
  }

}
