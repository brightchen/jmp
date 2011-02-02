package cg.dbmanagement.config;

public class DbManagerPropertyName
{
  public static enum NAME
  {
    databases( "dbmanager.supported.databases" );
    
    private String propertyKey;
    
    private NAME( String propertyKey )
    {
      this.propertyKey = propertyKey;
    }
    
    public String getPropertyKey()
    {
      return propertyKey;
    }
  }
}
