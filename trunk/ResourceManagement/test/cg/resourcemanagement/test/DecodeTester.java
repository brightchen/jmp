package cg.resourcemanagement.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DecodeTester
{
  public static void main( String[] argv )
  {
    defaultCharset();
//    fromFile();
  }
  
  public static void defaultCharset()
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
  }

  public static void fromFile()
  {
    try
    {
      File file = new File( "C:\\jmp\\ResourceManagement\\bin\\T2Resource_zh_CN.properties" );
      boolean bUseReader = false;
      String str;
      if( bUseReader )
      {
        FileReader fr = new FileReader( file );
        
        char[] content = new char[256];
        int length = fr.read( content );
        str = new String( content, 0, length );
      }
      else
      {
        // use input stream
        FileInputStream fis = new FileInputStream( file );
        byte bContent[] = new byte[512];
        int length = fis.read( bContent );
        str = new String( bContent, 0, length );
      }
      testChars( str );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  public static void testChars( String s1 )
  {
    String[] charsets = { "UTF-8", "UTF-16", null };
    for( String charset1 : charsets )
    {
      for( String charset2 : charsets )
      {
        try
        {
          String value = new String( s1.getBytes( charset1 ), charset2 );
          System.out.println( String.format( "getBytes( %s ), new String(%s): %s", charset1, charset2, value ) );
        }
        catch( Exception e )
        {
          System.out.println( "Exception: " + e.getMessage() );
        }

      }
    }
  }
  
  public static void testBytes()
  {
    byte[] bs1 = { -25, -95, -82, -27, -82, 63 };
    byte[] bs2 = { (byte)0xE7, (byte)0xA1, (byte)0xAE, (byte)0xE5, (byte)0xAE, (byte)0x9A  };
    
    byte[][] bss = {bs1, bs2};
    
    String[] charsets = { "UTF-8", "UTF-16", null };
    for( byte[] bs : bss )
    {
      for( String charset : charsets )
      {
        String value = "";
        try
        {
          if( charset == null )
            value = new String();
          else
            value = new String( bs, charset );
          System.out.println( charset + ": " + value );
        }
        catch( Exception e )
        {
          System.out.println( "Exception: " + e.getMessage() );
        }
      }
      
      System.out.println( "======================" );
    }
  }
}
