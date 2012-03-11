package cg.resourcemanagement;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;

import cg.common.util.DataReference;
import cg.resourcemanagement.util.LocaleUtil;


/*
 * 1. register all the resources
 * ResourceBundle
 * Locale
 */
public class ResourceManager
{
  private Logger log = Logger.getLogger( ResourceManager.class );
  private static ResourceManager instance;
  
  //  resource bundle base name ==> ( localeName ==> resource bundle ), String is more efficient than Locale as key
  private Map< String, Map< String, ResourceBundle > > resourcesGroups = new HashMap< String, Map< String, ResourceBundle > >();;
  
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
      DataReference< Locale > localeRef = new DataReference< Locale >();
      DataReference< String > baseNameRef = new DataReference< String >();
      lookup.getLookupStrategy().getResourceInfo( file, localeRef, baseNameRef );

      ResourceBundle rb = loadResourceBundle( baseNameRef.getData(), localeRef.getData() );
      if( rb == null )
        continue;
      
      //UserManagementResource.properties, the loaded ResourceBundle's locale is "en US"
      putResource( localeRef.getData(), baseNameRef.getData(), rb );
    }
  }
  
  protected ResourceBundle loadResourceBundle( String baseName, Locale locale )
  {
    return locale!= null ? ResourceBundle.getBundle( baseName, locale ) : ResourceBundle.getBundle( baseName );
  }
  
  protected void putResource( Locale locale, String baseName, ResourceBundle resourceBundle )
  {
    Map< String, ResourceBundle > bundles = resourcesGroups.get( baseName );
    if( bundles == null )
    {
      bundles = new HashMap< String, ResourceBundle >();
      resourcesGroups.put( baseName, bundles );
    }
    bundles.put( LocaleUtil.getLocaleName( locale ), resourceBundle );
  }
  
  protected ResourceFileLookup getResourceFileLookup()
  {
    return new ResourceFileLookup();
  }

  public Set< Locale > getSupportedLocales( String resourceBaseName )
  {
    return LocaleUtil.getLocales( getSupportedLocaleNames( resourceBaseName ) );
  }
  
  public Set< String > getSupportedLocaleNames( String resourceBaseName )
  {
    // the TOP locale in fact is not a locale, get rid of it from the list
    // the keySet is a reference. should clone it
    Map< String, ResourceBundle > resourceBundleMap = resourcesGroups.get( resourceBaseName );
    if( resourceBundleMap == null )
    {
      log.error( "No resource group form base name: " + resourceBaseName );
      return Collections.emptySet();
    }
    
    Set< String > supportedLocales = new HashSet< String >();
    for( String localeName : resourceBundleMap.keySet() )
    {
      if( LocaleUtil.getLocaleName( LocaleUtil.TOP_LOCALE ).equals( localeName ) )
        continue;
      supportedLocales.add( localeName );
    }
    return supportedLocales;
  }
  
  public Set<String> getKeys( Locale locale )
  {
    Set< String > keys = new HashSet< String >();

    for( Map.Entry< String, Map< String, ResourceBundle > >group : resourcesGroups.entrySet() )
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
  
  public String getString( String resourceBaseName, Locale locale, String key ) throws MissingResourceException
  {
    Map< String, ResourceBundle > group = resourcesGroups.get( resourceBaseName );
    if( group == null )
      return null;
    
    for( ; locale != null; locale = getParentLocale( locale ) )
    {
      ResourceBundle bundle = group.get( LocaleUtil.getLocaleName( locale ) );
      if( bundle != null )
      {
        String value = bundle.getString( key );
        return decodeString( locale, value );
      }
    }
    return null;
  }

  protected Locale getParentLocale( Locale locale )
  {
    if( LocaleUtil.TOP_LOCALE.equals( locale ) )
      return null;
    String country = locale.getCountry();
    return ( country != null && !country.isEmpty() ) ? new Locale( locale.getLanguage() ) : LocaleUtil.TOP_LOCALE;
  }
  
  public String getString( Locale locale, String key )
  {
    for( String baseName : resourcesGroups.keySet() )
    {
      try
      {
        String value = getString( baseName, locale, key );
        if( value != null )
          return value;
      }
      catch( MissingResourceException mre )
      {
        //try another base name
      }
    }
    return null;
  }

  /*
   * some resources are kept as UTF-8 in the resource file, should convert to the UTF-16 before use it
   */
  protected static String decodeString( Locale locale, String value )
  {
    if( !shouldDecode( locale ) )
      return value;
    try
    {
      //the ResourceBundle read resource file as "ISO-8859-1", we using "UTF-8" inside for resource 
      return new String( value.getBytes( "ISO-8859-1" ), "UTF-8" ); 
    }
    catch( Exception e )
    {
      return value;
    }
  }
  
  protected static boolean shouldDecode( Locale locale )
  {
    //only Chinese need to decode right now
    return Locale.CHINESE.getLanguage().equals( locale.getLanguage() );
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
