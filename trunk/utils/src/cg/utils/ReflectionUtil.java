package cg.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Vector;

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
        Object data = method.invoke( source, (Object[])null );
        Method setMethod = getMethod( destClass, setMethodName, new Object[]{ data } ); //destClass.getMethod( setMethodName, (Class<?>[])null );
        if( setMethod == null )
          continue;
        setMethod.invoke( destClass, data );
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
      
    }
  }
  
  /*
   * get the method from class which the method name is <methodName>, and the parameters are compatible to <parameters> 
   */
  public static Method getMethod( Class<?> clazz, String methodName, Object[] parameters )
  {
    Method[] methods = getMethods( clazz, methodName );
    if( methods == null || methods.length == 0 )
      return null;
    
    //check the parameter compatibility
    for( Method method : methods )
    {
      Class<?>[] parameterTypes = method.getParameterTypes();
      if( isParametersCompatible( parameters, parameterTypes ) )
        return method;
    }
    return null;
  }
  
  public static boolean isParametersCompatible( Object[] parameters, Class<?>[] parameterTypes )
  {
    if( parameters == null || parameters.length == 0 )
    {
      return ( parameterTypes == null || parameterTypes.length == 0 );
    }
    if( parameterTypes == null || parameterTypes.length == 0 )
      return false;
    if( parameters.length != parameterTypes.length )
      return false;
    
    for( int index = 0; index < parameters.length; ++index )
    {
      if( !isParameterCompatible( parameters[index], parameterTypes[index] ) )
        return false;
    }
    return true;
  }
  
  /*
   * precondition: the parameter can be null, but parameterType can NOT be null;
   */
  public static boolean isParameterCompatible( Object parameter, Class<?> parameterType )
  {
    if( parameter == null )
      return true;
    if( parameterType == null )
      return false;
    //TODO: need to check primitive etc
    return parameterType.isAssignableFrom( parameter.getClass() );
  }
  
  public static Method[] getMethods( Class<?> clazz, String methodName )
  {
    Method[] methods = clazz.getMethods();
    Vector< Method > satisfiedMethods = new Vector< Method >();
    for( Method method : methods )
    {
      if( method.getName().equals( methodName ) && isValidMethod( method ) )
      {
        satisfiedMethods.add( method );
      }
    }
    return ( satisfiedMethods == null || satisfiedMethods.size() == 0 ) ? null : satisfiedMethods.toArray( new Method[ satisfiedMethods.size() ] );
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
