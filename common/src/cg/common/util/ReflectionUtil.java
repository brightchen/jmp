package cg.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

@SuppressWarnings( "rawtypes" ) 
public class ReflectionUtil
{
  public static final String GET_METHOD_PATTERN = "get.+";
  public static final String SET_METHOD_PATTERN = "set.+";
  public static final String GET_METHOD_PREFIX = "get";
  public static final String SET_METHOD_PREFIX = "set";
  
  public static final Class<?>[] NO_PARAMETER = new Class<?>[]{};
  public static final Object[] IGNORE_PARAMETERS = null;     //don't care about the parameters
  

  
  
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
    
    //Class.getMethods() only returns the public methods
    Method[] allMethods = clazz.getMethods();
    if( Modifier.isProtected( expectedModifiers ) || Modifier.isPrivate( expectedModifiers ) )
    {
      //FIXME: getDeclaredMethods() only returns the methods declared in this class but not the super classes.
      //allMethods = clazz.getDeclaredMethods();    
    }
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
  
  public static <T> Set< Field > getFields( Class<T> clazz, Class< ? super T > rootSuperClass )
  {
    Set< Field > allFields = new HashSet< Field >();
    for( Class< ? super T > curClazz = clazz; rootSuperClass.isAssignableFrom( curClazz ); curClazz = curClazz.getSuperclass() )
    {
      //most fields are declared as private, use getDeclaredFields() instead of getFields()
      Field[] fields = clazz.getDeclaredFields();
      allFields.addAll( Arrays.asList( fields ) );
    }
    return allFields;
  }

  @SuppressWarnings( "unchecked")
  public static Field getField( Class<?> clazz, String propertyName, Class<?> type )
  {
    return getField( (Class)clazz, (Class)clazz, propertyName, type );
  }

  public static <T> Field getField( Class<T> clazz, Class< ? super T > rootSuperClass, String propertyName, Class<?> type )
  {
    return getField( getFields( clazz, rootSuperClass ), propertyName, type );
  }
  
  public static <T> Field getField( Set< Field > fields, String propertyName, Class<?> type )
  {
    if( fields == null || fields.size() == 0 )
      return null;
    for( Field  field : fields )
    {
      if( field.getName().equals( propertyName ) && isParameterTypeCompatible( field.getType(), type ) )
        return field;
    }
    return null;
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
    //we can call set(null)
    //so, isParameterTypeCompatible( any-type, null ) returns true;
    if( expectedParameterType == null )
      return true;
    if( parameterType == null )
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
      if( method.getName().equals( methodName ) && isValidPublicMethod( method ) )
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

  public static boolean isValidPublicMethod( Method method )
  {
    int modifiers = method.getModifiers();
    if( Modifier.isAbstract( modifiers ) || !Modifier.isPublic( modifiers ) )
      return false;
    return true;
  }
  
  public static boolean isTypicalGetterMethod( Method method )
  {
    if( !isGetterMethod( method ) )
      return false;
    if( !isValidPublicMethod( method ) )
      return false;
    
    //getter shouldn't take parameter and have return value
    Class<?>[] parameterTypes = method.getParameterTypes();
    if( parameterTypes != null && parameterTypes.length != 0 )
      return false;
    
    return ( method.getReturnType() != null );
    
  }

  public static boolean isTypicalSetterMethod( Method method )
  {
    if( !isSetterMethod( method ) )
      return false;
    if( !isValidPublicMethod( method ) )
      return false;
    
    //setter method should have one parameter and without return type
    Class<?>[] parameterTypes = method.getParameterTypes();
    if( parameterTypes == null || parameterTypes.length != 1 )
      return false;
    
    return ( method.getReturnType() == null );
    
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
   * so, the actual type of generic class for B is String.class
   */
  @SuppressWarnings( "unchecked" )
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
  
  /*
   * get the Getter/Setter if input method is Setter/Getter
   * return the map using the input method as key and corresponding method as value
   */
  public static Map< Method, Method > getCorrespondingGetterSetters( Set< Method > methods )
  {
    if( methods == null )
      return null;
    
    Map< Method, Method > map = new HashMap< Method, Method >();
    for( Method method : methods )
    {
      map.put( method, getCorrespondingGetterSetter( method ) );
    }
    return map;
  }
  
  public static Map< Method, Method > getCorrespondingSetters( Set< Method > getters )
  {
    if( getters == null )
      return null;
    
    Map< Method, Method > map = new HashMap< Method, Method >();
    for( Method method : getters )
    {
      map.put( method, getCorrespondingSetter( method ) );
    }
    return map;
  }
  
  public static Map< Method, Method > getCorrespondingGetters( Set< Method > setters )
  {
    if( setters == null )
      return null;
    
    Map< Method, Method > map = new HashMap< Method, Method >();
    for( Method method : setters )
    {
      map.put( method, getCorrespondingGetter( method ) );
    }
    return map;
  }
  
  
  public static Method getCorrespondingGetterSetter( Method method )
  {
    if( method == null )
      return null;
    String methodName = method.getName();
    if( methodName.matches( GET_METHOD_PATTERN ) )
      return getCorrespondingSetter( method );
    else if( methodName.matches( SET_METHOD_PATTERN )  )
      return getCorrespondingGetter( method );
    return null;
  }
  
  public static Method getCorrespondingSetter( Method getterMethod )
  {
    if( !getterMethod.getName().matches( GET_METHOD_PATTERN ) )
      return null;
    
    final String getterMethodName = getterMethod.getName();
    final String setterMethodName = "s" + getterMethodName.substring( 1 );
    
    //get parameter/return value type
    Class<?> parameterReturnValueType = getterMethod.getReturnType();
    
    //usually, the getter/setter are defined in same class
    Set< Method > methods = getMethods( getterMethod.getDeclaringClass(), setterMethodName, new Class<?>[]{ parameterReturnValueType }, Modifier.PUBLIC );
    return ( methods == null || methods.size() == 0 ) ? null : methods.iterator().next();
  }
  
  
  public static Method getCorrespondingGetter( Method setterMethod )
  {
    if( !setterMethod.getName().matches( SET_METHOD_PATTERN ) )
      return null;

    final String setterMethodName = setterMethod.getName();
    final String getterMethodName = "g" + setterMethodName.substring( 1 );
    
    //usually, the getter/setter are defined in same class
    Set< Method > methods = getMethods( setterMethod.getDeclaringClass(), getterMethodName, NO_PARAMETER, Modifier.PUBLIC );
    if( methods == null || methods.size() == 0 ) 
      return null;
    
    Method getterMethod = methods.iterator().next();
    if( getterMethod == null )
      return null;
    
    //check the return value of getter
    //get parameter/return value type
    Class<?>[] parameterTypes = setterMethod.getParameterTypes();
    if( parameterTypes == null || parameterTypes.length != 1 )
      return null;  //this is not a really setter, the setter should take and only take one parameter;
    Class<?> parameterReturnValueType = parameterTypes[0];
    
    return isParameterTypeCompatible( getterMethod.getReturnType(), parameterReturnValueType ) ? getterMethod : null;
    
  }
}
