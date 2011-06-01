package cg.config;

import java.io.File;
import java.net.URL;

/*
 * the typical strategy for lookup properties file
 * The properties file is locate as following logic
 * 1. if the propertiesFilePathProperty ( path = directory + fileName ) is set and the file is valid properties file, return this file
 * 2.1. if propertiesFileNameProperty is set, get the properties file name; else use the defaultPropertiesFileName as file name
 * 2.2. search in the class path and find the first valid properties file which name is same as 2.1
 */
 
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
    // try to get properties file from propertiesFilePathProperty
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
    
    //try to get properties file from propertiesFileNameProperty
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
