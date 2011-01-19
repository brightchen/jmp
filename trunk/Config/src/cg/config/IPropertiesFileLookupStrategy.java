package cg.config;

import java.io.File;

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
