package cg.studio.reflections;

import java.net.URL;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class SubTypeReflections
{
  private static Reflections subTypeReflections;
  
  private static Reflections getSubTypeReflections()
  {
    Set<URL> urls = ClasspathHelper.getUrlsForCurrentClasspath();
    for( URL url : urls )
    {
      System.out.println( "url: " + url.toExternalForm() );
    }
    
    FilterBuilder fb = new FilterBuilder().include( "cg.studio.*" );
    subTypeReflections = new Reflections( new ConfigurationBuilder().filterInputsBy( fb ).setUrls(urls).setScanners( new SubTypesScanner() ) );
//    subTypeReflections = new Reflections( new ConfigurationBuilder().setUrls(urls).setScanners( new SubTypesScanner() ) );

    return subTypeReflections;
  }
  
  public static <T> Set< Class< ? extends T > > findSubTypes( Class< T > service )
  {
    Set< Class< ? extends T > > subTypes = getSubTypeReflections().getSubTypesOf( service );
    return ( subTypes == null || subTypes.isEmpty() ) ? null : subTypes;
  }
}
