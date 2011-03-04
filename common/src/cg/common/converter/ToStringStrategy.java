package cg.common.converter;

public class ToStringStrategy implements IConvertStrategy
{
  @SuppressWarnings( "unchecked" )
  public <T> T convert( Object origin, Class<T> convertToType ) throws ConvertNotSupportException
  {
    if( !convertToType.equals( String.class ) )
      throw new ConvertNotSupportException( "ToStringStrategy not support convert object to String" );
    return (T)origin.toString();
  }
}
