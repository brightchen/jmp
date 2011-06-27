package cg.resourcemanagement;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;


/*
 * 1. register all the resources
 * ResourceBundle
 * Locale
 */
public class ResourceManager
{
  private static ResourceManager instance;
  
  // use one resource for one locale. merge the resources which for same locale
  private Map< Locale, ResourceBundle > localedResource = new HashMap< Locale, ResourceBundle >();;
  
  public static ResourceManager getInstance()
  {
    if( instance == null )
    {
      synchronized( ResourceManager.class )
      {
        if( instance == null )
        {
          instance = new ResourceManager();
          instance.init();
        }
      }
    }
    
    return instance;
  }

  /*
   * load resources and merge
   */
  protected void init()
  {
    ResourceFileLookup lookup = getResourceFileLookup();
    Set< String > files = new HashSet< String >();  //lookup.getResourceFiles();
    files.clear();
    files.add( "cg\\resourcemanagement\\TestResource.properties" );
    if( files == null || files.size() == 0 )
      return;
    
    for( String file : files )
    {
      ResourceBundle rb = loadResourceBundle( file );
      if( rb == null )
        continue;

      Locale locale = rb.getLocale();
      putResource( locale, rb );
    }
  }
  
  protected String getResourceBundleBaseName( String file )
  {
    return ( file.endsWith( ".properties" ) ) ? file.substring( 0, file.length() - ".properties".length() ) : file;
  }
  protected ResourceBundle loadResourceBundle( String file )
  {
    // convert the file into the bundle name
    // the input file is file path, like cg/ResourceManagement/TestResource.properties
    String bundleName = getResourceBundleBaseName( file );
    return ResourceBundle.getBundle( bundleName );
  }
  
  protected void putResource( Locale locale, ResourceBundle resourceBundle )
  {
    ResourceBundle rb = localedResource.get( locale );
    if( rb == null )
    {
      localedResource.put( locale, resourceBundle );
    }
    else
    {
      //merge the resource
    }
  }
  
  protected ResourceFileLookup getResourceFileLookup()
  {
    return new ResourceFileLookup();
  }

  public Enumeration<String> getKeys( Locale locale )
  {
    ResourceBundle rb = getResourceBundle( locale );
    if( rb == null )
      return null;
    return rb.getKeys();
    
  }
  
  public String getString( Locale locale, String key )
  {
    ResourceBundle rb = getResourceBundle( locale );
    if( rb == null )
      return null;
    return rb.getString( key );
  }

  public String[] getStringArray( Locale locale, String key )
  {
    ResourceBundle rb = getResourceBundle( locale );
    if( rb == null )
      return null;
    return rb.getStringArray( key );
  }

  public ResourceBundle getResourceBundle( Locale locale )
  {
    return localedResource.get( locale );
  }
}
