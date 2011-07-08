package cg.resourcemanagement.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import cg.resourcemanagement.ResourceManager;

public class ResourceTester
{
  public static void main( String[] argv )
  {
    testDecode();
//    normalTest();
  }
  
  public static void testDecode()
  {
    try
    {
      FileWriter filewrt = new FileWriter("out");
      String defaultcharset = filewrt.getEncoding();
      System.out.println( "default charset is: " + defaultcharset );
    }
    catch( IOException e )
    {
      e.printStackTrace();
    }
    
    try
    {
    Locale chinaLocale = Locale.CHINA;
    String str = ResourceManager.getInstance().getString( chinaLocale , "ok" );
    byte[] bstr = str.getBytes();       //how can bstr[5] is 63? it should be -102
//    bstr[5] = (byte)-102;
    
    str = new String( bstr, 0, bstr.length, "UTF-8" );
    System.out.println( str );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
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
    Locale[] locales = { Locale.ENGLISH, Locale.US, Locale.CHINA, Locale.CHINESE  };
    
    for( Locale locale : locales )
    {
      System.out.println( "==========Locale: " + locale.getDisplayName() + "===================" );
      Set<String> keys = rm.getKeys( locale );
      if( keys == null )
      {
        continue;
      }
  
      for( String key : keys )
      {
        String value = rm.getString( locale, key );
        System.out.println( String.format( "%s = %s ", key, value ) );
      }
    }
  }
}
