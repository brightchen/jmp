package cg.resourcemanagement;

import java.util.Locale;
import java.util.Set;

import cg.common.util.DataReference;


public interface IResourceFileLookupStrategy
{
  /*
   * return the file path which required for ResourceBundle
   */
  public Set<String> getResourceFiles();
  
  /*
   * get the Locale and base name of this resource
   */
  public boolean getResourceInfo( String resourceFileName, DataReference< Locale > locale, DataReference< String > baseName );
}
