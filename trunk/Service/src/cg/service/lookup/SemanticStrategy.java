package cg.service.lookup;

import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

//this strategy select the service provider by the classes which implements the interface.
//return any one if there are more than one implements
public class SemanticStrategy implements IServiceLookupStrategy
{
  private static Reflections subTypeReflections;
  
  private static Reflections getSubTypeReflections()
  {
    if( subTypeReflections == null )
    {
      synchronized( SemanticStrategy.class )
      {
        if( subTypeReflections == null )
        {
          subTypeReflections = new Reflections( new ConfigurationBuilder().filterInputsBy( getFilterBuilder() ).setScanners( new SubTypesScanner() ) );
        }
      }
    }
    return subTypeReflections;
  }
  
  //TODO: add the package configuration limitation to increase the performance
  private static FilterBuilder getFilterBuilder()
  {
    return new FilterBuilder().include( "cg.*" );
  }
  
  @Override
  public < T > T findService( Class< T > service ) throws ServiceNotFoundException
  {
    Set< Class< ? extends T > > subTypes = getSubTypeReflections().getSubTypesOf( service );
    for( Class< ? extends T > subType : subTypes )
    {
      try
      {
        if( ServiceUtil.isImplementor( service, subType ) )
          return subType.newInstance();
      }
      catch( Exception e )
      {
      }
    }
    return null;
  }

}
