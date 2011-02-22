package cg.service.lookup;

public interface IServiceLookup
{
  public <T, I extends T> I findService( Class<T> service ) throws ServiceNotFoundException;
}
