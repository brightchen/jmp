package cg.utils;


public class DataConverter
{
  /*
   * the source object and destination object have the same getter/setter method 
   * the method copy the data from source to the destination
   */
  public static void shallowCopyConvert( Object source, Object dest )
  {
    ReflectionUtil.shallowCopy( source, dest );
  }
}
