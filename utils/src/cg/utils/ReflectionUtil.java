package cg.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUtil
{
  private static final String GET_METHOD_PREFIX = "get";
  private static final String SET_METHOD_PREFIX = "set";

  /*
   * the source object and destination object have the same getter/setter method 
   * the method copy the data from source to the destination
   */
  public static void shallowCopy( Object source, Object dest )
  {
    if( source == null || dest == null )
      return;
    
    Class<?> sourceClass = source.getClass();
    Method[] sourceMethods = sourceClass.getMethods();
    
    Class<?> destClass = dest.getClass();

    for( Method method : sourceMethods )
    {
      if( !isValidGetMethod( method ) )
        continue;
      String setMethodName = getCorrespondingSetMethodName( method.getName() );
      try
      {
        Method setMethod = destClass.getMethod( setMethodName, (Class<?>[])null );
        Object data = method.invoke( source, (Object[])null );
        setMethod.invoke( destClass, data );
      }
      catch( Exception e )
      {
      }
      
    }
  }

  public static boolean isGetMethod( Method method )
  {
    String methodName = method.getName();
    return ( methodName.length() > GET_METHOD_PREFIX.length() && methodName.startsWith( GET_METHOD_PREFIX ) );
  }

  public static boolean isSetMethod( Method method )
  {
    String methodName = method.getName();
    return ( methodName.length() > SET_METHOD_PREFIX.length() && methodName.startsWith( SET_METHOD_PREFIX ) );
  }

  public static boolean isValidMethod( Method method )
  {
    int modifiers = method.getModifiers();
    if( Modifier.isAbstract( modifiers ) || !Modifier.isPublic( modifiers ) )
      return false;
    return true;
  }
  
  public static boolean isValidGetMethod( Method method )
  {
    if( !isGetMethod( method ) )
      return false;
    return isValidMethod( method );
  }

  public static boolean isValidSetMethod( Method method )
  {
    if( !isSetMethod( method ) )
      return false;
    return isValidMethod( method );
  }

  public static String getCorrespondingSetMethodName( String getMethodName )
  {
    return getMethodName.replaceFirst( GET_METHOD_PREFIX, SET_METHOD_PREFIX );
  }
  public static String getCorrespondingGetMethodName( String setMethodName )
  {
    return setMethodName.replaceFirst( SET_METHOD_PREFIX, GET_METHOD_PREFIX );
  }

}
