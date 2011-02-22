package cg.service.lookup;

//this class is a facade of service lookup.
//it use LocalServiceLookup or RemoteServiceLookup to get/create the service implementor
public class ServiceManager
{
  //TODO: handle the remote service lookup
  public <T, I extends T> I findService( Class<T> service ) throws ServiceNotFoundException
  {
    return LocalServiceLookup.getServiceLookup().findService( service );
  }
}
