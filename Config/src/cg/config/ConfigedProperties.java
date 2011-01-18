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
  protected static enum PropertyFileType
  {
    properties,
    xml
  }

  // the property with keep the information of properties file name
  protected String propertiesFileNameProperty;

  // the default properties file name
  protected String defaultPropertiesFileName;

  public static Properties getProperties()
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
  

  protected static String getPropertyFilePath()
  {
    //get from property first
    String propertyFileName = null;
    propertyFileName = System.getProperty( propertiesFileNameProperty );
    if( propertyFileName != null && !propertyFileName.isEmpty() )
      return propertyFileName;
    
    //get from classpath
    for( PropertyFileType suffix : PropertyFileType.values() )
    {
      propertyFileName = defaultPropertiesFileName + "." + suffix.name();
      URL propertyUrl = ClassLoader.getSystemResource( propertyFileName );
      if( propertyUrl == null )
        continue;
      return propertyUrl.getPath();
    }
    return null;
  }
  
  protected static PropertyFileType getSuffix( String filePath )
  {
    for( PropertyFileType suffix : PropertyFileType.values() )
    {
      if( filePath.endsWith( suffix.name() ) )
        return suffix;
    }
    throw new RuntimeException( "Invlid filePath: " + filePath );
  }
  
  public static void main( String argv[] )
  {
    System.out.println( getPropertyFilePath() );
  }
}
