package cg.common.converter;

public class Converter
{
  protected IConvertStrategy strategy;
  
  public static enum StrategyEnum
  {
    toStringStrategy( ToStringStrategy.class );
    
    private Class<?> strategy;
    private <T extends IConvertStrategy> StrategyEnum( Class< T > strategy )
    {
      this.strategy = strategy;
    }
    
    public Class<?> getStrategyClass()
    {
      return strategy;
    }
  }

  private Converter( IConvertStrategy strategy )
  {
    this.strategy = strategy;
  }
  
  public static Converter getConverter()
  {
    return null;
  }
  
  public static Converter getConverter( StrategyEnum strategyEnum )
  {
    try
    {
      IConvertStrategy strategy = (IConvertStrategy)strategyEnum.getStrategyClass().newInstance();
      return new Converter( strategy );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static <T> T convertType( Object origin, Class<T> convertToType, StrategyEnum strategy ) throws ConvertNotSupportException
  {
    return getConverter( strategy ).convert( origin, convertToType );
  }
  
  public static <T> T convertType( Object origin, Class<T> convertToType ) throws ConvertNotSupportException
  {
    return getConverter().convert( origin, convertToType );
  }
  
  public <T> T convert( Object origin, Class<T> convertToType ) throws ConvertNotSupportException
  {
    return strategy.convert( origin, convertToType );
  }
}
