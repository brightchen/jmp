package cg.resourcemanagement;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import cg.utils.DataReference;


/*
 * 1. register all the resources
 * ResourceBundle
 * Locale
 */
public class ResourceManager
{
  private static final String suffix = ".properties";
  private static ResourceManager instance;
  
  // a locale ==> ( resource bundle base name ==> resource bundle )
  private Map< Locale, Map< String, ResourceBundle > > localedResource = new HashMap< Locale, Map< String, ResourceBundle > >();;
  
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
    Set< String > files = lookup.getResourceFiles();
//    Set< String > files = new HashSet< String >();  //lookup.getResourceFiles();
//    files.clear();
//    files.add( "cg\\resourcemanagement\\TestResource.properties" );
    if( files == null || files.size() == 0 )
      return;
    
    for( String file : files )
    {
      // convert the file into the bundle name
      // the input file is file path, like cg/ResourceManagement/TestResource.properties
      DataReference< Locale > locale = new DataReference< Locale >();
      DataReference< String > baseName = new DataReference< String >();
      lookup.getLookupStrategy().getResourceInfo( file, locale, baseName );

      ResourceBundle rb = loadResourceBundle( baseName.getData(), locale.getData() );
      if( rb == null )
        continue;

      //
      putResource( rb.getLocale(), baseName.getData(), rb );
    }
  }
  
  protected ResourceBundle loadResourceBundle( String baseName, Locale locale )
  {
    return locale!= null ? ResourceBundle.getBundle( baseName, locale ) : ResourceBundle.getBundle( baseName );
  }
  
  protected void putResource( Locale locale, String baseName, ResourceBundle resourceBundle )
  {
    Map< String, ResourceBundle > bundles = localedResource.get( locale );
    if( bundles == null )
    {
      bundles = new HashMap< String, ResourceBundle >();
      localedResource.put( locale, bundles );
    }
    bundles.put( baseName, resourceBundle );
  }
  
  protected ResourceFileLookup getResourceFileLookup()
  {
    return new ResourceFileLookup();
  }

  public Set<String> getKeys( Locale locale )
  {
    Map< String, ResourceBundle > bundles = localedResource.get( locale );
    Set< String > keys = new HashSet< String >();
    for( Map.Entry< String, ResourceBundle > entry : bundles.entrySet() )
    {
      ResourceBundle bundle = entry.getValue();
      Enumeration< String > theKeys = bundle.getKeys();
      while( theKeys.hasMoreElements() )
      {
        keys.add( theKeys.nextElement() );
      }
    }
    return keys;
  }
  
  public String getString( Locale locale, String key )
  {
    Map< String, ResourceBundle > bundles = localedResource.get( locale );
    for( Map.Entry< String, ResourceBundle > entry : bundles.entrySet() )
    {
      ResourceBundle bundle = entry.getValue();
      try
      {
        return bundle.getString( key );
      }
      catch( MissingResourceException e )
      {
      }
    }
    throw new MissingResourceException( "", "", key );
  }

//  public String[] getStringArray( Locale locale, String key )
//  {
//    ResourceBundle rb = getResourceBundle( locale );
//    if( rb == null )
//      return null;
//    return rb.getStringArray( key );
//  }

//  public ResourceBundle getResourceBundles( Locale locale )
//  {
//    return localedResource.get( locale ).;
//  }
}
