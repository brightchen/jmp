package cg.persistence.config;

public class PersistencePropertyName
{
  public static enum PROPERTY_NAME
  {
    DRIVER_CLASS( "db.jdbc.driver_class" ),
    URL( "db.jdbc.url" ),
    USERNAME( "db.username" ),
    PASSWORD( "db.password" ),
    VALIDATION_QUERY( "db.validation.query" );

    private String propertyKey;
    
    private PROPERTY_NAME( String propertyKey )
    {
      this.propertyKey = propertyKey;
    }
    
    public String getPropertyKey()
    {
      return propertyKey;
    }
  }
}
