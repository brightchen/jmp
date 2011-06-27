package cg.resourcemanagement;

import java.io.File;

public class ResourceTester
{
  public static void main( String[] argv )
  {
    ResourceFileLookup lookup = new ResourceFileLookup();
    File[] files = lookup.getResourceFiles();
    if( files == null || files.length == 0 )
    {
      System.out.println( "NO resource file found " );
      return;
    }
    
    for( File file : files )
    {
      System.out.println( "Found resource file: " + file.getPath() );
    }
  }
}
