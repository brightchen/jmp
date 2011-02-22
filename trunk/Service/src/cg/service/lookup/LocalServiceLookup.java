package cg.service.lookup;

//find service
public class LocalServiceLookup implements IServiceLookup
{
  public static enum ServiceLookupEnum
  {
    DEFAULT_CHAIN( StrategyChainStrategy.class ),
    CACHE( CacheStrategy.class ),
    SEMANTIC( SemanticStrategy.class );
    
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
  
  private static ServiceLookupEnum DEFAULT_LOOKUP_ENUM = ServiceLookupEnum.DEFAULT_CHAIN;

  //TODO: only need one instance for each strategy
  public static LocalServiceLookup getServiceLookup( ServiceLookupEnum serviceLookupEnum )
  {
    return new LocalServiceLookup( serviceLookupEnum );
  }
  
  //get the service lookup using the default strategy
  public static LocalServiceLookup getServiceLookup()
  {
    return getServiceLookup( DEFAULT_LOOKUP_ENUM );
  }
  
  private IServiceLookupStrategy lookupStrategy;
  
  public LocalServiceLookup()
  {
  }
  
  public LocalServiceLookup( ServiceLookupEnum serviceLookupEnum )
  {
    this( );
    setLookupStrategy( getServiceLookupStrategy( serviceLookupEnum ) );
  }
  
  public LocalServiceLookup( IServiceLookupStrategy lookupStrategy )
  {
    this.lookupStrategy = lookupStrategy;
  }
  
  
  protected IServiceLookupStrategy getServiceLookupStrategy( ServiceLookupEnum serviceLookupEnum )
  {
    Class< ? extends IServiceLookupStrategy > strategyClass = serviceLookupEnum.getStrategyClass();
    if( ServiceLookupEnum.DEFAULT_CHAIN.equals( strategyClass ) )
    {
      return getDefaultChainStrategy();
    }
    try
    {
      return strategyClass.newInstance();
    }
    catch( Exception e )
    {
      return null;
    }
  }
  
  protected IServiceLookupStrategy getDefaultChainStrategy()
  {
    IServiceLookupStrategy strategy = new StrategyChainStrategy();
    //setup the chain here;
    return strategy;
  }
  
  @Override
  public <T, I extends T> I findService( Class<T> service ) throws ServiceNotFoundException
  {
    return lookupStrategy.findService( service );
  }

  public IServiceLookupStrategy getLookupStrategy()
  {
    return lookupStrategy;
  }

  public void setLookupStrategy( IServiceLookupStrategy lookupStrategy )
  {
    this.lookupStrategy = lookupStrategy;
  }
  
}
