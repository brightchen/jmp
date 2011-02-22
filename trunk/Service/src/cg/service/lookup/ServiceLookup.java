package cg.service.lookup;

//find service
public class ServiceLookup
{
  public static enum ServiceLookupEnum
  {
    CHAIN( StrategyChainStrategy.class );
    
    private Class< ? extends IServiceLookupStrategy > strategyClass;
    private ServiceLookupEnum( Class< ? extends IServiceLookupStrategy > strategyClass )
    {
      this.strategyClass = strategyClass;
    }
    
    public Class< ? extends IServiceLookupStrategy > getStrategyClass()
    {
      return strategyClass;
    }
  }
  
  
  public static ServiceLookup getServiceLookup( ServiceLookupEnum serviceLookupEnum )
  {
    Class< ? extends IServiceLookupStrategy > strategyClass = serviceLookupEnum.getStrategyClass();
    IServiceLookupStrategy strategy = null;
    try
    {
      strategy = strategyClass.newInstance();
      return new ServiceLookup( strategy );
    }
    catch( Exception e )
    {
    }
    return null; 
  }
  
  //get the service lookup using the default strategy
  public static ServiceLookup getServiceLookup()
  {
    return getServiceLookup( ServiceLookupEnum.CHAIN );
  }
  
  private IServiceLookupStrategy lookupStrategy;
  
  private ServiceLookup( IServiceLookupStrategy lookupStrategy )
  {
    this.lookupStrategy = lookupStrategy;
  }
  
  public <T, I extends T> I findService( Class<T> service ) throws ServiceNotFoundException
  {
    return null;
  }
}
