package cg.common.property;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javassist.Modifier;
import cg.common.util.CollectionUtil;
import cg.common.util.ReflectionUtil;

public class ClassPropertyUtil
{
  // where the property come from
  public static enum PropertyCriteria
  {
    Field,            //has field
    Getter,           //has getter method
    Setter,           //has setter method
    GetterAndSetter,  //has both getter and setter
    GetterOrSetter,   //has either getter or setter
    FieldOrGetterOrSetter
  }
  
  public static PropertyCriteria defaultPropertyCriteria = PropertyCriteria.GetterAndSetter;
  
  public static <T> Set< ClassProperty > getClassProperties( Class<T> clazz )
  {
    return getClassProperties( clazz, clazz );
  }
  
  public static <T> Set< ClassProperty > getClassProperties( Class<T> clazz, Class< ? super T > rootSuperClass )
  {
    return getClassProperties( clazz, rootSuperClass, defaultPropertyCriteria );
  }
  
  // return true if the criteria depended on getter
  public static boolean dependedOnGetter( PropertyCriteria criteria )
  {
    return criteria.name().indexOf( PropertyCriteria.Getter.name() ) >= 0;
  }
  public static boolean dependedOnSetter( PropertyCriteria criteria )
  {
    return criteria.name().indexOf( PropertyCriteria.Setter.name() ) >= 0;
  }
  public static boolean dependedOnField( PropertyCriteria criteria )
  {
    return criteria.name().indexOf( PropertyCriteria.Field.name() ) >= 0;
  }

  /*
   * get properties from class clazz and its super classes until rootSuperClass.
   * the reflection getMethod() will get all the methods defined in class hierarchy
   */
  public static <T> Set< ClassProperty > getClassProperties( Class<T> clazz, Class< ? super T > rootSuperClass, PropertyCriteria criteria )
  {
    Set< ClassProperty > getters = null;
    if( dependedOnGetter( criteria ) )
    {
      getters = getClassGetterProperties( clazz, rootSuperClass );
      if( PropertyCriteria.Getter.equals( criteria ) )
        return getters;
    }
    
    Set< ClassProperty > setters = null;
    if( dependedOnSetter( criteria ) )
    {
      setters = getClassSetterProperties( clazz, rootSuperClass );
      if( PropertyCriteria.Setter.equals( criteria ) )
          return setters;
    }

    Set< ClassProperty > fields = null;
    if( dependedOnField( criteria ) )
    {
      fields = getClassFieldProperties( clazz, rootSuperClass );
      if( PropertyCriteria.Field.equals( criteria ) )
          return fields;
    }

    if( PropertyCriteria.GetterAndSetter.equals( criteria ) )
      CollectionUtil.retainAllByValue( getters, setters );
    else if( PropertyCriteria.GetterOrSetter.equals( criteria ) ) // PropertyCriteria.GetterOrSetter
      CollectionUtil.addAllByValue( getters, setters );
    else //FieldOrGetterOrSetter
    {
      CollectionUtil.addAllByValue( getters, setters );
      CollectionUtil.addAllByValue( getters, fields );
    }
    return getters;
  }
  
  public static <T> Set< ClassProperty > getClassGetterProperties( Class<T> clazz, Class< ? super T > rootSuperClass )
  {
    Set< Method > getterMethods = ReflectionUtil.getMethods( clazz, rootSuperClass, ReflectionUtil.GET_METHOD_PATTERN, new Class<?>[]{}, Modifier.PUBLIC );
    return getProperties( getterMethods );
  }

  /*
   * we don't care about the parameters list of setter right now
   * TODO: check the parameter list of setter
   */
  public static <T> Set< ClassProperty > getClassSetterProperties( Class<T> clazz, Class< ? super T > rootSuperClass )
  {
    Set< Method > setterMethods = ReflectionUtil.getMethods( clazz, rootSuperClass, ReflectionUtil.SET_METHOD_PATTERN, null, Modifier.PUBLIC );
    return getProperties( setterMethods );
  }
  
  public static <T> Set< ClassProperty> getClassFieldProperties( Class<T> clazz, Class< ? super T > rootSuperClass )
  {
    //get fields from class
  }
  
  public static Set< ClassProperty > getProperties( Set< Method > methods )
  {
    Set< ClassProperty > properties = new HashSet< ClassProperty >();
    for( Method method : methods )
    {
      ClassProperty property = new ClassProperty( getPropertyName( method ), method.getDeclaringClass() );
      properties.add( property );
    }
    return properties;
  }

  /*
   * get the property name from setter/getter;
   */
  public static String getPropertyName( Method method )
  {
    String methodName = method.getName();
    String propertyName = methodName.substring( 3 );
    return propertyName.substring( 0, 1 ).toLowerCase() + propertyName.substring( 1 );
  }
  
  public static String getGetterName( String propertyName )
  {
    return ReflectionUtil.GET_METHOD_PREFIX + propertyName.substring( 0, 1 ).toUpperCase() + propertyName.substring( 1 );
  }

  public static String getSetterName( String propertyName )
  {
    return ReflectionUtil.SET_METHOD_PREFIX + propertyName.substring( 0, 1 ).toUpperCase() + propertyName.substring( 1 );
  }

}
