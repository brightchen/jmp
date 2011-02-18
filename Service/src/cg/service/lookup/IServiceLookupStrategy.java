package cg.service.lookup;

public interface IServiceLookupStrategy
{
  public <T, I extends T> I findService( Class<T> service ) throws ServiceNotFoundException;
}
