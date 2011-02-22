package cg.service.lookup;

import java.util.HashMap;
import java.util.Map;

//this strategy main the map of the service interface and implement.
//the cache map was built when founded the mapped service interface and implement
public class CacheStrategy implements IServiceLookupStrategy
{
  private Map< Class<?>, Class<?> > serviceImplementMap = new HashMap< Class<?>, Class<?> >();
  
  
  @Override
  @SuppressWarnings( "unchecked" )
  public < T, I extends T > I findService( Class< T > service ) throws ServiceNotFoundException
  {
    for( Class<?> key : serviceImplementMap.keySet() )
    {
      if( key.equals( service ) )
        return (I)serviceImplementMap.get( key );
    }
    return null;
  }

  public void addEntry( Class<?> service, Class<?> serviceImplementor )
  {
    //make sure serviceImplement implement the service
    ServiceUtil.ensureImplementor( service, serviceImplementor );
    serviceImplementMap.put( service, serviceImplementor );
  }
}
