package cg.config;

import java.io.File;

/*
 * the strategy for lookup properties file
 */
public interface IPropertiesFileLookupStrategy
{
  public static enum PropertiesFileType
  {
    properties,
    xml
  }

  public File findPropertiesFile();
  
  // the PropertiesFileType of the found properties file
  public PropertiesFileType getPropertiesFileType();
}
