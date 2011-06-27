package cg.resourcemanagement;

import java.io.File;
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
  public File[] getResourceFiles()
  {
    Reflections resouceReflections = new Reflections( ( new ReflectionsBuilder() ).getTypicalConfigurationBuilder().setScanners( new ResourceNameFormatScanner() ) );
    Set< String > fileNames = resouceReflections.getResources( (Pattern)null );//( Pattern.compile( "{\\w}*Resource.properties" ) );
    if( fileNames == null || fileNames.size() == 0 )
      return null;
    File[] files = new File[ fileNames.size() ];
    int index = 0;
    for( String fileName : fileNames )
    {
      files[ index ] = new File( fileName );
      ++index;
    }
    return files;
  }
  
  static class ResourceNameFormatScanner extends ResourcesScanner
  {
    @Override
    public boolean acceptsInput( String fileName ) 
    {
      return fileName.length() > "Resource.properties".length() && fileName.endsWith( "Resource.properties" ); //not a class
    }
  }
}
