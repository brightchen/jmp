package cg.resourcemanagement;

import java.util.Set;


public interface IResourceFileLookupStrategy
{
  /*
   * return the file path which required for ResourceBundle
   */
  public Set<String> getResourceFiles();
}
