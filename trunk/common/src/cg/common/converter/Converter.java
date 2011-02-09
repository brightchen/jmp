package cg.common.converter;

public class Converter
{
  protected IConvertStrategy strategy;
  
  public static enum CONVERT_STRATEGY
  {
    
  }
  
  public static Converter getConverter()
  {
    return null;
  }
  
  public static Converter getConverter( CONVERT_STRATEGY strategy )
  {
    return null;
  }
  
  public static <T> T convert( Object origin, Class<T> convertToType, CONVERT_STRATEGY strategy )
  {
    return getConverter( strategy ).convert( origin, convertToType );
  }
  
  public <T> T convert( Object origin, Class<T> convertToType )
  {
    return null;
  }
}
