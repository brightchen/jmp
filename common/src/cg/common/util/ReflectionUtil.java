package cg.common.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class ReflectionUtil
{
  public static final String GET_METHOD_PATTERN = "get.+";
  public static final String SET_METHOD_PATTERN = "set.+";
  
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
        Method setMethod = getMethod( destClass, setMethodName, getParameterTypes( new Object[]{ data } ) ); //destClass.getMethod( setMethodName, (Class<?>[])null );
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
  
  
  public static Set< Method > getMethods( Class<?> clazz, String methodNamePattern, Class<?>[] expectedParameterTypes, int expectedModifiers )
  {
    return getMethods( clazz, null, methodNamePattern, expectedParameterTypes, expectedModifiers );
  }
  
  /*
   * get the methods which match following criteria from class clazz
   * 1. the methods is declared in the class hierarchy [ clazz, rootSuperClass ] inclusively
   * 2. the methods' name is match methodNamePattern
   * 3. the parameter type list is compatible with expectedParameterTypes
   * 4. the methods' modifiers are match expectedModifiers
   */
  public static <T> Set< Method > getMethods( Class<T> clazz, Class< ? super T > rootSuperClass, String methodNamePattern, 
                                              Class<?>[] expectedParameterTypes, int expectedModifiers ) 
  {
    Set< Method > methods = new HashSet< Method >(); 
    Method[] allMethods = clazz.getMethods();
    if( allMethods == null || allMethods.length == 0 )
      return methods;
    
    for( Method method : allMethods )
    {
      if( rootSuperClass != null )
      {
        // the qualify method should be declared in the class which is sub-class of rootSuperClass inclusively
        if( !rootSuperClass.isAssignableFrom( method.getDeclaringClass() ) )
          continue;
      }
      if( isMethodMetch( method, methodNamePattern, expectedParameterTypes, expectedModifiers ) )
        methods.add( method );
    }
    return methods;
  }


  
  /*
   * get the method from class which the method name is <methodName>, and the parameters are compatible to <parameters> 
   * parameters - the parameter list instead of parameter type list
   */
  public static Method getMethod( Class<?> clazz, String methodName, Class<?>[] parameterTypes )
  {
    Set< Method > methods = getMethods( clazz, methodName, parameterTypes, Modifier.PUBLIC );
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
  
  /*
   * if expectedParameterTypes is null: means the caller don't care about the parameter types
   * if length of expectedParameterTypes is 0: means the method should not have parameter.
   */
  public static boolean isParameterTypesCompatible( Class<?>[] parameterTypes, Class<?>[] expectedParameterTypes )
  {
    if( expectedParameterTypes == null )
    {
      return true;
    }
    if( expectedParameterTypes.length == 0 )
      return ( parameterTypes.length == 0 );
    
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
   * precondition: parameterType should NOT be null;
   */
  public static boolean isParameterTypeCompatible( Class<?> parameterType, Class<?> expectedParameterType )
  {
    if( expectedParameterType == null || parameterType == null )
      throw new IllegalArgumentException( "Neither parameter type nor expected parameter type should be null." );

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
    return methodName.matches( GET_METHOD_PATTERN );
  }

  public static boolean isSetterMethod( Method method )
  {
    String methodName = method.getName();
    return methodName.matches( SET_METHOD_PATTERN );
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

  public static Class<?>[] getParameterTypes( Object[] parameters )
  {
    Class<?>[] parameterTypes = null; 
    if( parameters != null && parameters.length > 0 )
    {
      parameterTypes = new Class<?>[ parameters.length ];
      for( int index = 0; index < parameters.length; ++index )
      {
        Object parameter = parameters[ index ];
        parameterTypes[ index ] = ( parameter == null ? null : parameter.getClass() );
      }
    }
    return parameterTypes;

  }
  
  /*
   * get the actual type of  generic class
   * class A< T >{}
   * class B extends A< String >{}
   */
  public static <T, A> Class< ? extends A > getGenericActualTypeArgumentClass( Class<T> clazz, Class< ? super T > genericClass, Class< A > actualTypeArgumentsClass )
  {
    for( Class< ? super T > superClass = clazz; !superClass.equals( genericClass ); superClass = superClass.getSuperclass()  )
    {
      Type type = superClass.getGenericSuperclass();
      if( type instanceof ParameterizedType )
      {
        Type[] arguments = ( (ParameterizedType)type ).getActualTypeArguments();
        for( Type argument : arguments )
        {
          if( ( argument instanceof Class ) && ( actualTypeArgumentsClass.isAssignableFrom( (Class)argument ) ) )
            return (Class< ? extends A >)argument;
        }
      }
    }
    
    return null;
  }
}
