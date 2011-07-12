package cg.resourcemanagement;

import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

import cg.common.reflect.ReflectionsBuilder;
import cg.common.util.DataReference;

/*
 * find the resource files by resource file name format
 * typically, the name of resource files have following format:
 *   - the name include the "Resource"
 *   - the name ends with ".properties" 
 */
public class ResourceFileLookupByNameFormat implements IResourceFileLookupStrategy
{
  private String resourceFileNameSuffix = "properties";
  private String resourceFileNameKeyWord = "Resource";
  private String resourceFileNamePattern = ".*" + resourceFileNameKeyWord + ".*\\." + resourceFileNameSuffix;
  
  public ResourceFileLookupByNameFormat(){}
  
  public ResourceFileLookupByNameFormat( String resourceFileNamePattern )
  {
    setResourceFileNamePattern( resourceFileNamePattern );
  }
  
  @Override
  public Set< String > getResourceFiles()
  {
    //use ResourceNameFormatScanner does not work when getResources(), why???
    Reflections resouceReflections = new Reflections( ( new ReflectionsBuilder() ).getTypicalConfigurationBuilder().setScanners( new ResourcesScanner() ) );
    return resouceReflections.getResources( Pattern.compile( resourceFileNamePattern ) ); //"Resource.properties$"
  }
  
  /*
   * get the Locale and base name of this resource
   */
  @Override
  public boolean getResourceInfo( String resourceFileName, DataReference< Locale > locale, DataReference< String > baseName )
  {
    String suffix = "." + resourceFileNameSuffix;
    resourceFileName = ( resourceFileName.endsWith( suffix ) ) 
                     ? resourceFileName.substring( 0, resourceFileName.length() - suffix.length() ) 
                     : resourceFileName;
                     
    int keyWordIndex = resourceFileName.indexOf( resourceFileNameKeyWord );
    
    //use the "." as the base name's seperator
    baseName.setData( resourceFileName.substring( 0, keyWordIndex + resourceFileNameKeyWord.length() ).replaceAll( "/", "." ) );
    
    //after keyword is the locale information
    String localeInfo = resourceFileName.substring( keyWordIndex + resourceFileNameKeyWord.length() );
    if( localeInfo.length() > 0 )
    {
      localeInfo = localeInfo.substring( 1 );    // remove the first '_'
      int offset = localeInfo.indexOf( '_' );

      if( offset > 0 )
      {
        //have language and country
        locale.setData( new Locale( localeInfo.substring( 0, offset ), localeInfo.substring( offset + 1 ) ) );
      }
      else
      {
        //only have language
        locale.setData( new Locale( localeInfo ) );
      }
    }
    return true;
  }

  
  
  public String getResourceFileNameSuffix()
  {
    return resourceFileNameSuffix;
  }

  public void setResourceFileNameSuffix( String resourceFileNameSuffix )
  {
    this.resourceFileNameSuffix = resourceFileNameSuffix;
  }

  public String getResourceFileNamePattern()
  {
    return resourceFileNamePattern;
  }

  public void setResourceFileNamePattern( String resourceFileNamePattern )
  {
    this.resourceFileNamePattern = resourceFileNamePattern;
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
