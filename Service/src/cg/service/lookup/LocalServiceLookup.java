package cg.service.lookup;

import java.util.EnumMap;
import java.util.Map;

//find service
//there only need one instance for each strategy
public class LocalServiceLookup implements IServiceLookup
{
  public static enum ServiceLookupStrategyEnum
  {
    DEFAULT_CHAIN( StrategyChainStrategy.class ),
    CACHE( CacheStrategy.class ),
    SEMANTIC( SemanticStrategy.class );
    
    private Class< ? extends IServiceLookupStrategy > strategyClass;
    private ServiceLookupStrategyEnum( Class< ? extends IServiceLookupStrategy > strategyClass )
    {
      this.strategyClass = strategyClass;
    }
    
    public Class< ? extends IServiceLookupStrategy > getStrategyClass()
    {
      return strategyClass;
    }
  }
  
  private static ServiceLookupStrategyEnum DEFAULT_LOOKUP_ENUM = ServiceLookupStrategyEnum.DEFAULT_CHAIN;
  private static Map< ServiceLookupStrategyEnum, LocalServiceLookup > serviceLookupMap = new EnumMap< ServiceLookupStrategyEnum, LocalServiceLookup >( ServiceLookupStrategyEnum.class );
  
  public static LocalServiceLookup getServiceLookup( ServiceLookupStrategyEnum serviceLookupEnum )
  {
    LocalServiceLookup lookup = serviceLookupMap.get( serviceLookupEnum );
    if( lookup == null )
    {
      synchronized( LocalServiceLookup.class )
      {
        lookup = serviceLookupMap.get( serviceLookupEnum );
        if( lookup == null )
        {
          lookup = new LocalServiceLookup( serviceLookupEnum );
          serviceLookupMap.put( serviceLookupEnum, lookup );
        }
      }
    }
    return lookup;
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
  
  public LocalServiceLookup( ServiceLookupStrategyEnum serviceLookupEnum )
  {
    this( );
    setLookupStrategy( getServiceLookupStrategy( serviceLookupEnum ) );
  }
  
  public LocalServiceLookup( IServiceLookupStrategy lookupStrategy )
  {
    this.lookupStrategy = lookupStrategy;
  }
  
  
  protected IServiceLookupStrategy getServiceLookupStrategy( ServiceLookupStrategyEnum serviceLookupEnum )
  {
    if( ServiceLookupStrategyEnum.DEFAULT_CHAIN.equals( serviceLookupEnum ) )
    {
      return getDefaultChainStrategy();
    }

    try
    {
      Class< ? extends IServiceLookupStrategy > strategyClass = serviceLookupEnum.getStrategyClass();
      return strategyClass.newInstance();
    }
    catch( Exception e )
    {
      return null;
    }
  }
  
  protected IServiceLookupStrategy getDefaultChainStrategy()
  {
    StrategyChainStrategy chainStrategy = new StrategyChainStrategy();
    chainStrategy.addStrategy( new CacheStrategy() );
    chainStrategy.addStrategy( new SemanticStrategy() );
    //setup the chain here;
    return chainStrategy;
  }
  
  @Override
  public <T> T findService( Class<T> service ) throws ServiceNotFoundException
  {
    T serviceImplementor = lookupStrategy.findService( service );
    LocalServiceLookup cacheLookup = serviceLookupMap.get( ServiceLookupStrategyEnum.CACHE );
    if( cacheLookup != null )
    {
      CacheStrategy cacheStrategy = (CacheStrategy)cacheLookup.getLookupStrategy();
      cacheStrategy.addEntry( service, serviceImplementor );
    }
    return serviceImplementor;
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
