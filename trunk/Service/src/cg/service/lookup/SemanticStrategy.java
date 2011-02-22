package cg.service.lookup;

//this strategy select the service provider by the classes which implements the interface.
//return any one if there are more than one implements
public class SemanticStrategy implements IServiceLookupStrategy
{
  @Override
  public < T, I extends T > I findService( Class< T > service ) throws ServiceNotFoundException
  {
    // TODO Auto-generated method stub
    return null;
  }

}
