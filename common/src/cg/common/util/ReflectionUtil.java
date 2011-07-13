package cg.common.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;
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
      if( !isValidGetterMethod( method ) )
        continue;
      String setMethodName = getCorrespondingSetMethodName( method.getName() );
      try
      {
        Object data = method.invoke( source, (Object[])null );
        Method setMethod = getMethod( destClass, setMethodName, new Object[]{ data } ); //destClass.getMethod( setMethodName, (Class<?>[])null );
        if( setMethod == null )
          continue;
        setMethod.invoke( dest, data );
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
      
    }
  }
  
  public static Set< Method > getMethods( Class<?> clazz, String methodNamePattern, Object[] expectedParameters, int expectedModifiers )
  {
    Class<?>[] expectedParameterTypes = null; 
    if( expectedParameters != null && expectedParameters.length > 0 )
    {
      expectedParameterTypes = new Class<?>[ expectedParameters.length ];
      for( int index = 0; index < expectedParameters.length; ++index )
      {
        Object parameter = expectedParameters[ index ];
        expectedParameterTypes[ index ] = ( parameter == null ? null : parameter.getClass() );
      }
    }
    
    return getMethods( clazz, methodNamePattern, expectedParameterTypes, expectedModifiers );
                                                      
  }

  public static Set< Method > getMethods( Class<?> clazz, String methodNamePattern, Class<?>[] expectedParameterTypes, int expectedModifiers )
  {
    Set< Method > methods = new HashSet< Method >(); 
    Method[] allMethods = clazz.getMethods();
    if( allMethods == null || allMethods.length == 0 )
      return methods;
    
    for( Method method : methods )
    {
      if( isMethodMetch( method, methodNamePattern, expectedParameterTypes, expectedModifiers ) )
        methods.add( method );
    }
    return methods;
  }


  
  /*
   * get the method from class which the method name is <methodName>, and the parameters are compatible to <parameters> 
   */
  public static Method getMethod( Class<?> clazz, String methodName, Object[] parameters )
  {
    Set< Method > methods = getMethods( clazz, methodName, parameters, Modifier.PUBLIC );
    return ( methods == null || methods.size() == 0 ) ? null : methods.iterator().next();
  }
  
  public static boolean isMethodMetch( Method method, String methodNamePattern, Class<?>[] expectedParameterTypes, int expectedModifiers )
  {
    if( methodNamePattern != null && methodNamePattern.length() > 0 && !method.getName().matches( methodNamePattern ) )
      return false;
    
    if( !isModifiedMatch( method.getModifiers(), expectedModifiers ) )
      return false;
    
    if( !isParameterTypesCompatible( method.getParameterTypes(), expectedParameterTypes) )
      return false;
    
    return true;
  }
  
  /*
   * check the accessible modifier and others
   */
  public static boolean isModifiedMatch( int modifiers, int expectedModifiers )
  {
    // accessible
    if( Modifier.isPrivate( expectedModifiers ) && !Modifier.isPrivate( modifiers ) )
      return false;
    if( Modifier.isProtected( expectedModifiers ) && !Modifier.isProtected( modifiers ) )
      return false;
    if( Modifier.isPublic( expectedModifiers ) && !Modifier.isPublic( modifiers ) )
      return false;

    //abstract
    if( Modifier.isAbstract( expectedModifiers ) && !Modifier.isAbstract( modifiers ) )
      return false;
    
    //static 
    if( Modifier.isStatic( expectedModifiers ) && !Modifier.isStatic( modifiers ) )
      return false;

    return true;
  }
  
  public static boolean isParameterTypesCompatible( Class<?>[] parameterTypes, Class<?>[] expectedParameterTypes )
  {
    if( parameterTypes == null || parameterTypes.length == 0 )
    {
      return ( expectedParameterTypes == null || expectedParameterTypes.length == 0 );
    }
    if( expectedParameterTypes == null || expectedParameterTypes.length == 0 )
      return false;
    if( parameterTypes.length != expectedParameterTypes.length )
      return false;
    
    for( int index = 0; index < parameterTypes.length; ++index )
    {
      if( !isParameterTypeCompatible( parameterTypes[index], expectedParameterTypes[index] ) )
        return false;
    }
    return true;
  }
  
  /*
   * precondition: the parameter can be null, but parameterType can NOT be null;
   */
  public static boolean isParameterTypeCompatible( Class<?> parameterType, Class<?> expectedParameterType )
  {
    if( expectedParameterType == null && parameterType == null )
      return true;

    if( expectedParameterType == null || parameterType == null )
      return false;

    //TODO: need to check primitive etc
    return expectedParameterType.isAssignableFrom( parameterType );
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

  public static boolean isGetterMethod( Method method )
  {
    String methodName = method.getName();
    return ( methodName.length() > GET_METHOD_PREFIX.length() && methodName.startsWith( GET_METHOD_PREFIX ) );
  }

  public static boolean isSetterMethod( Method method )
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
  
  public static boolean isValidGetterMethod( Method method )
  {
    if( !isGetterMethod( method ) )
      return false;
    return isValidMethod( method );
  }

  public static boolean isValidSetterMethod( Method method )
  {
    if( !isSetterMethod( method ) )
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
