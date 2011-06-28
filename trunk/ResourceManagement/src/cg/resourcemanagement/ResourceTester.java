package cg.resourcemanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceTester
{
  public static void main( String[] argv )
  {
    normalTest();
  }
  
  public static void testBundles()
  {
    String[] bundleNames = new String[]{ "cg\\resourcemanagement\\TestResource", 
                                         "cg\\resourcemanagement\\TestResource.properties", 
                                         "cg/resourcemanagement/TestResource.properties",
                                   "cg/resourcemanagement/TestResource",
                                   "TestResource", 
                                   "cg.resourcemanagement.TestResource" };

    for( String bundleName : bundleNames )
    {
      try
      {
      ResourceBundle rb = ResourceBundle.getBundle( bundleName );
      if( rb == null )
        System.out.println( "can't load bundle: " + bundleName );
      else
        System.out.println( "load bundle successful: " + bundleName );
      }
      catch( Exception e )
      {
        System.out.println( "load bundle exception: " + bundleName + "; " + e.toString() );
      }
    }
  }
  public static void testFile()
  {
    String[] files = new String[]{ "cg\\resourcemanagement\\TestResource.properties", 
                                   "cg/resourcemanagement/TestResource.properties",
                                   "TestResource.properties", 
                                   "cg.resourcemanagement.TestResource.properties" };
    for( String file : files )
    {
      try
      {
        File f = new File( file );
        FileReader fr = new FileReader( f );
        fr.close();
        
        System.out.println( "file " + file + " is OK " );
      }
      catch( FileNotFoundException e )
      {
        System.out.println( "can't find file " + file );
      }
      catch( IOException e )
      {
        System.out.println( "IOException " + file );
      }

    }
  }
  
  public static void normalTest()
  {
    ResourceManager rm = ResourceManager.getInstance();
    Locale[] locales = { Locale.ENGLISH, Locale.US  };
    
    for( Locale locale : locales )
    {
      System.out.println( "==========Locale: " + locale.getDisplayName() + "===================" );
      Enumeration<String> keys = rm.getKeys( locale );
      if( keys == null )
      {
        continue;
      }
  
      while( keys.hasMoreElements()  )
      {
        String key = keys.nextElement();
        String value = rm.getString( locale, key );
        System.out.println( String.format( "%s = %s ", key, value ) );
      }
    }
  }
}
