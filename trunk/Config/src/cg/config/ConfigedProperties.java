package cg.config;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;


// the properties with load/save from/to config file
// the config file maybe properties file or xml file
// the schema of f
public class ConfigedProperties extends Properties
{
  private static final long serialVersionUID = -1496935754867075190L;

  public Properties getProperties()
  {
    String filePath = getPropertyFilePath();
    if( filePath == null )
      return null;
    
    Properties props = new Properties();
    
    //check property file existance
    File propertyFile = new File( filePath );
    if( !propertyFile.exists() )
    {
      return props;
    }
    
    PropertyFileType suffix = getSuffix( filePath );
    try
    {
      FileInputStream propertyFileIs = new FileInputStream( propertyFile );
      if( PropertyFileType.properties.equals( suffix ) )
      {
        props.load( propertyFileIs );
      }
      if( PropertyFileType.xml.equals( suffix ) )
      {
        props.loadFromXML( propertyFileIs );
      }
      propertyFileIs.close();
    }
    catch( Exception e )
    {
    }
    return props;
  }
  

  protected String getPropertyFilePath()
  {
  }
  
  protected PropertyFileType getSuffix( String filePath )
  {
    for( PropertyFileType suffix : PropertyFileType.values() )
    {
      if( filePath.endsWith( suffix.name() ) )
        return suffix;
    }
    throw new RuntimeException( "Invlid filePath: " + filePath );
  }
}
