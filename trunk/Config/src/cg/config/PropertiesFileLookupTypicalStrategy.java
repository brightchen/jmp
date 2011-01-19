package cg.config;

import java.io.File;
import java.net.URL;

// 1. file name
// 1.1 propertiesFileNameProperty
// 1.2 default properties file name
// 2. file path: class path
// 
public class PropertiesFileLookupTypicalStrategy implements IPropertiesFileLookupStrategy
{
  protected static enum PropertiesFileType
  {
    properties,
    xml
  }

  // the property with keep the information of properties file path
  protected String propertiesFilePathProperty;
  // the property with keep the information of properties file name
  protected String propertiesFileNameProperty;
  // the default properties file name
  protected String defaultPropertiesFileName;

  @Override
  public File findPropertiesFile()
  {
    String propertiesFilePath = System.getProperty( propertiesFilePathProperty );
    if( propertiesFilePath != null && !propertiesFilePath.isEmpty() && doesPropertiesFileExist( propertiesFilePath ) )
    {
      return new File( propertiesFilePath );
    }
    
    //get from property first
    String propertiesFileName = System.getProperty( propertiesFileNameProperty );
    if( propertiesFileName == null || propertiesFileName.isEmpty() )
    {
      propertiesFileName = defaultPropertiesFileName;
    }
    
    //search the properties file with the classpath
    URL propertyUrl = ClassLoader.getSystemResource( propertiesFileName );
    if( propertyUrl == null )
      return null;
    propertiesFilePath = propertyUrl.getPath();
    if( !doesPropertiesFileExist( propertiesFilePath ) )
      return null;
    return new File( propertiesFilePath );
  }
  
  // the filePath should be properties file path and the file exists
  protected boolean doesPropertiesFileExist( String filePath )
  {
    if( filePath == null || filePath.isEmpty() )
      return false;

    //file type
    boolean couldBePropertiesFilePath = false;
    for( PropertiesFileType fileType : PropertiesFileType.values() )
    {
      if( filePath.endsWith( fileType.name() ) )
      {
        couldBePropertiesFilePath = true;
        break;
      }
    }
    if( !couldBePropertiesFilePath )
      return false;
    
    //file existence
    File file = new File( filePath );
    return( file.exists() && file.isFile() );
  }

}
