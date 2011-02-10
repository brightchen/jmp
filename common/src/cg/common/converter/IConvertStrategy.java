package cg.common.converter;

public interface IConvertStrategy
{
  public <T> T convert( Object origin, Class<T> convertToType ) throws ConvertNotSupportException;
}
