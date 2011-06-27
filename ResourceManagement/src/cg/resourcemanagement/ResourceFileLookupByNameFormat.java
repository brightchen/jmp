package cg.resourcemanagement;

import java.util.Set;
import java.util.regex.Pattern;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

import cg.common.reflect.ReflectionsBuilder;

/*
 * find the resource files by resource file name format
 * typically, the name of resource files have following format:
 *   - the name include the "Resource"
 *   - the name ends with ".properties" 
 */
public class ResourceFileLookupByNameFormat implements IResourceFileLookupStrategy
{
  @Override
  public Set< String > getResourceFiles()
  {
    //use ResourceNameFormatScanner does not work when getResources(), why???
    Reflections resouceReflections = new Reflections( ( new ReflectionsBuilder() ).getTypicalConfigurationBuilder().setScanners( new ResourcesScanner() ) );
    return resouceReflections.getResources( Pattern.compile( ".*Resource.*\\.properties" ) ); //"Resource.properties$"
  }
  
  /*
   * interesting, why use ResourceNameFormatScanner does not work when getResources()?
   */
  static class ResourceNameFormatScanner extends ResourcesScanner
  {
    @Override
    public boolean acceptsInput( String fileName ) 
    {
      return fileName.length() >= "Resource.properties".length() && fileName.endsWith( "Resource.properties" ); //not a class
    }
  }
}
