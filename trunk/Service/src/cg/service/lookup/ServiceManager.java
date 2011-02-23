package cg.service.lookup;

//this class is a facade of service lookup.
//it use LocalServiceLookup or RemoteServiceLookup to get/create the service implementor
public class ServiceManager
{
  //TODO: handle the remote service lookup
  public static <T> T findService( Class<T> service ) throws ServiceNotFoundException
  {
    return LocalServiceLookup.getServiceLookup().findService( service );
  }
}
