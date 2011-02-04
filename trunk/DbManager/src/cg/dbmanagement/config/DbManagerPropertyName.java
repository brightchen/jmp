package cg.dbmanagement.config;

public class DbManagerPropertyName
{
  public static enum PROPERTY_NAME
  {
    databases( "dbmanager.supported.databases" );
    
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
