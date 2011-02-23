package cg.service.lookup;

import java.util.ArrayList;
import java.util.List;

//this class main a chain of strategy, and search the service using the strategy one by one.
//it returns the first found service implement
//this class does not weight which strategy is more suitable, it's client's responsibility
public class StrategyChainStrategy implements IServiceLookupStrategy
{
  private List< IServiceLookupStrategy > strategyChain;
  
  @Override
  public <T> T findService( Class<T> service ) throws ServiceNotFoundException
  {
    if( strategyChain == null || strategyChain.size() == 0 )
      throw new ServiceNotFoundException( "strategy chain is empty" );
    for( IServiceLookupStrategy strategy : strategyChain )
    {
      T implement = strategy.findService( service );
      if( implement != null )
        return implement;
    }
    throw new ServiceNotFoundException( "No service found" );
  }


  public List< IServiceLookupStrategy > getStrategyChain()
  {
    return strategyChain;
  }
  public void setStrategyChain( List< IServiceLookupStrategy > strategyChain )
  {
    this.strategyChain = strategyChain;
  }

  public void addStrategy( IServiceLookupStrategy strategy )
  {
    if( strategyChain == null )
    {
      strategyChain = new ArrayList< IServiceLookupStrategy >();
    }
    strategyChain.add( strategy );
  }
  
}
