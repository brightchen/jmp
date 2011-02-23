package cg.service.lookup;

public interface IServiceLookup
{
  public <T> T findService( Class<T> service ) throws ServiceNotFoundException;
}
