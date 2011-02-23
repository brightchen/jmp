package cg.service.lookup;

import java.util.HashMap;
import java.util.Map;

//this strategy main the map of the service interface and implement.
//the cache map was built when founded the mapped service interface and implement
public class CacheStrategy implements IServiceLookupStrategy
{
  //keep the interface type and implement instance(instead of type)
  private Map< Class<?>, Object > serviceImplementMap = new HashMap< Class<?>, Object >();
  
  @Override
  @SuppressWarnings( "unchecked" )
  public < T > T findService( Class< T > service ) throws ServiceNotFoundException
  {
    for( Class<?> key : serviceImplementMap.keySet() )
    {
      if( key.equals( service ) )
        return (T)serviceImplementMap.get( key );
    }
    return null;
  }

  public <T> void addEntry( Class<T> service,  T serviceImplementor )
  {
    //make sure serviceImplement implement the service
    ServiceUtil.ensureImplementor( service, serviceImplementor.getClass() );
    serviceImplementMap.put( service, serviceImplementor );
  }
}
