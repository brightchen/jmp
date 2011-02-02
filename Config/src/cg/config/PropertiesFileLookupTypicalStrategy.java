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
  // the property with keep the information of properties file path
  protected String propertiesFilePathProperty;
  // the property with keep the information of properties file name
  protected String propertiesFileNameProperty;
  // the default properties file name
  protected String defaultPropertiesFileName;
  
  protected PropertiesFileType propertiesFileType;

  @Override
  public File findPropertiesFile()
  {
    String propertiesFilePath;
    if( propertiesFilePathProperty != null && !propertiesFilePathProperty.isEmpty() )
    {
      propertiesFilePath = System.getProperty( propertiesFilePathProperty );
      if( propertiesFilePath != null && !propertiesFilePath.isEmpty() && doesPropertiesFileExist( propertiesFilePath ) )
      {
        propertiesFileType = getPropertiesFileType( propertiesFilePath );
        return new File( propertiesFilePath );
      }
    }
    
    //get from property first
    String propertiesFileName = null;
    if( propertiesFileNameProperty != null && !propertiesFileNameProperty.isEmpty() )
    {
      propertiesFileName = System.getProperty( propertiesFileNameProperty );
    }
    if( propertiesFileName == null || propertiesFileName.isEmpty() )
    {
      propertiesFileName = defaultPropertiesFileName;
    }
    
    //search the properties file with the classpath
    if( propertiesFileName == null || propertiesFileName.isEmpty() )
      return null;    
    URL propertyUrl = ClassLoader.getSystemResource( propertiesFileName );
    if( propertyUrl == null )
      return null;
    propertiesFilePath = propertyUrl.getPath();
    if( !doesPropertiesFileExist( propertiesFilePath ) )
      return null;
    
    propertiesFileType = getPropertiesFileType( propertiesFilePath );
    return new File( propertiesFilePath );
  }
  
  // the filePath should be properties file path and the file exists
  protected boolean doesPropertiesFileExist( String filePath )
  {
    PropertiesFileType fileType = getPropertiesFileType( filePath );
    if( fileType == null )
      return false;
    
    //file existence
    File file = new File( filePath );
    return( file.exists() && file.isFile() );
  }
  
  public PropertiesFileType getPropertiesFileType( String filePath )
  {
    if( filePath == null || filePath.isEmpty() )
      return null;
    for( PropertiesFileType fileType : PropertiesFileType.values() )
    {
      if( filePath.endsWith( fileType.name() ) )
      {
        return fileType;
      }
    }
    return null;
  }
  
  // the PropertiesFileType of the found properties file
  @Override
  public PropertiesFileType getPropertiesFileType()
  {
    return propertiesFileType;
  }

  public String getPropertiesFilePathProperty()
  {
    return propertiesFilePathProperty;
  }
  public void setPropertiesFilePathProperty( String propertiesFilePathProperty )
  {
    this.propertiesFilePathProperty = propertiesFilePathProperty;
  }

  public String getPropertiesFileNameProperty()
  {
    return propertiesFileNameProperty;
  }
  public void setPropertiesFileNameProperty( String propertiesFileNameProperty )
  {
    this.propertiesFileNameProperty = propertiesFileNameProperty;
  }

  public String getDefaultPropertiesFileName()
  {
    return defaultPropertiesFileName;
  }
  public void setDefaultPropertiesFileName( String defaultPropertiesFileName )
  {
    this.defaultPropertiesFileName = defaultPropertiesFileName;
  }
}
