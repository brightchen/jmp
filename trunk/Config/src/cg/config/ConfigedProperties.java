package cg.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


// the properties with load/save from/to config file
// the config file maybe properties file or xml file
public class ConfigedProperties extends Properties
{
  private static final long serialVersionUID = -1496935754867075190L;
  
  protected IPropertiesFileLookupStrategy propertiesFileLookupStrategy;
  
  public ConfigedProperties()
  {
  }

  public ConfigedProperties( IPropertiesFileLookupStrategy propertiesFileLookupStrategy )
  {
    setPropertiesFileLookupStrategy( propertiesFileLookupStrategy );
  }

  public Properties getProperties()
  {
    File propertiesFile = getPropertiesFile();
    if( propertiesFile == null )
      return new Properties();
    
    try
    {
      Properties props = new Properties();
      FileInputStream propertyFileIs = new FileInputStream( propertiesFile );
      IPropertiesFileLookupStrategy.PropertiesFileType propertiesFileType = getPropertiesFileLookupStrategy().getPropertiesFileType();
      
      if( IPropertiesFileLookupStrategy.PropertiesFileType.properties.equals( propertiesFileType ) )
      {
        props.load( propertyFileIs );
      }
      if( IPropertiesFileLookupStrategy.PropertiesFileType.xml.equals( propertiesFileType ) )
      {
        props.loadFromXML( propertyFileIs );
      }
      propertyFileIs.close();
      return props;
    }
    catch( Exception e )
    {
      return new Properties();
    }
  }
  

  protected File getPropertiesFile()
  {
    if( getPropertiesFileLookupStrategy() == null )
      return null;
    return getPropertiesFileLookupStrategy().findPropertiesFile();
  }


  public IPropertiesFileLookupStrategy getPropertiesFileLookupStrategy()
  {
    return propertiesFileLookupStrategy;
  }

  public void setPropertiesFileLookupStrategy( IPropertiesFileLookupStrategy propertiesFileLookupStrategy )
  {
    this.propertiesFileLookupStrategy = propertiesFileLookupStrategy;
  }
  
  
}
