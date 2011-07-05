package cg.resourcemanagement;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
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
  private static ResourceManager instance;
  private static final Locale TOP_LOCALE = new Locale( "" );   // a locale without language and country, it's the top locale 
  
  //  resource bundle base name ==> ( locale ==> resource bundle )
  private Map< String, Map< Locale, ResourceBundle > > resourcesGroups = new HashMap< String, Map< Locale, ResourceBundle > >();;
  
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

      // use rb.getLocale() instead of locale as locale can be null
      putResource( rb.getLocale(), baseName.getData(), rb );
    }
  }
  
  protected ResourceBundle loadResourceBundle( String baseName, Locale locale )
  {
    return locale!= null ? ResourceBundle.getBundle( baseName, locale ) : ResourceBundle.getBundle( baseName );
  }
  
  protected void putResource( Locale locale, String baseName, ResourceBundle resourceBundle )
  {
    Map< Locale, ResourceBundle > bundles = resourcesGroups.get( baseName );
    if( bundles == null )
    {
      bundles = new HashMap< Locale, ResourceBundle >();
      resourcesGroups.put( baseName, bundles );
    }
    bundles.put( locale, resourceBundle );
  }
  
  protected ResourceFileLookup getResourceFileLookup()
  {
    return new ResourceFileLookup();
  }

  public Set<String> getKeys( Locale locale )
  {
    Set< String > keys = new HashSet< String >();

    for( Map.Entry< String, Map< Locale, ResourceBundle > >group : resourcesGroups.entrySet() )
    {
      ResourceBundle bundle = group.getValue().get( locale );
      Enumeration< String > theKeys = bundle.getKeys();
      while( theKeys.hasMoreElements() )
      {
        keys.add( theKeys.nextElement() );
      }
    }
    return keys;
  }
  
  public String getString( String resourceBaseName, Locale locale, String key )
  {
    Map< Locale, ResourceBundle > group = resourcesGroups.get( resourceBaseName );
    if( group == null )
      return null;
    
    for( ; locale != null; locale = getParentLocale( locale ) )
    {
      ResourceBundle bundle = group.get( locale );
      if( bundle != null )
        return bundle.getString( key );
    }
    return null;
  }

  protected Locale getParentLocale( Locale locale )
  {
    if( TOP_LOCALE.equals( locale ) )
      return null;
    String country = locale.getCountry();
    return ( country != null && !country.isEmpty() ) ? new Locale( locale.getLanguage() ) : TOP_LOCALE;
  }
  
  public String getString( Locale locale, String key )
  {
    for( String baseName : resourcesGroups.keySet() )
    {
      String value = getString( baseName, locale, key );
      if( value != null )
        return value;
    }
    return null;
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
