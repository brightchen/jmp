package cg.common.property;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javassist.Modifier;
import cg.common.util.ReflectionUtil;

public class ClassPropertyUtil
{
  // where the property come from
  public static enum PropertyCriteria
  {
    Getter,           //has getter method
    Setter,           //has setter method
    GetterAndSetter,  //has both getter and setter
    GetterOrSetter    //has either getter or setter
  }
  
  public static PropertyCriteria defaultPropertyCriteria = PropertyCriteria.GetterAndSetter;
  
  public static <T> Set< ClassProperty > getClassProperties( Class<T> clazz, Class< ? super T > rootSuperClass )
  {
    return getClassProperties( clazz, rootSuperClass, defaultPropertyCriteria );
  }
  
  /*
   * get properties from class clazz and its super classes until rootSuperClass
   */
  public static <T> Set< ClassProperty > getClassProperties( Class<T> clazz, Class< ? super T > rootSuperClass, PropertyCriteria criteria )
  {
    Set< ClassProperty > properties = new HashSet< ClassProperty >();
    for( Class< ? super T > superClass = clazz; superClass != null && !superClass.equals( rootSuperClass ); superClass = superClass.getSuperclass()  )
    {
      properties.addAll( getClassPropertiesFlattly( superClass, criteria ) );
    }
    
    return properties;
  }
  
  /*
   * get the properties for this class only, it doesn't go through the class hierarchy
   */
  public static <T> Set< ClassProperty > getClassPropertiesFlattly( Class<T> clazz, PropertyCriteria criteria )
  {
    Set< ClassProperty > getters = null;
    if( PropertyCriteria.Getter.equals( criteria ) || PropertyCriteria.GetterAndSetter.equals( criteria ) 
        || PropertyCriteria.GetterOrSetter.equals( criteria ) )
    {
      getters = getClassGetterPropertiesFlattly( clazz );
      if( PropertyCriteria.Getter.equals( criteria ) )
        return getters;
    }
    
    Set< ClassProperty > setters = null;
    if( PropertyCriteria.Setter.equals( criteria ) || PropertyCriteria.GetterAndSetter.equals( criteria ) 
        || PropertyCriteria.GetterOrSetter.equals( criteria ) )
    {
      setters = getClassSetterPropertiesFlattly( clazz );
      if( PropertyCriteria.Setter.equals( criteria ) )
          return setters;
    }
    
    if( PropertyCriteria.GetterAndSetter.equals( criteria ) )
      getters.retainAll( setters );
    else  // PropertyCriteria.GetterOrSetter
      getters.addAll( setters );
    
    return getters;
  }
  
  public static <T> Set< ClassProperty > getClassGetterPropertiesFlattly( Class<T> clazz )
  {
    Set< Method > getterMethods = ReflectionUtil.getMethods( clazz, ReflectionUtil.GET_METHOD_PATTERN, new Class<?>[]{}, Modifier.PUBLIC );
    return getProperties( getterMethods );
  }

  /*
   * we don't care about the parameters list of setter right now
   * TODO: check the parameter list of setter
   */
  public static <T> Set< ClassProperty > getClassSetterPropertiesFlattly( Class<T> clazz )
  {
    Set< Method > setterMethods = ReflectionUtil.getMethods( clazz, ReflectionUtil.SET_METHOD_PATTERN, null, Modifier.PUBLIC );
    return getProperties( setterMethods );
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

  public static String getPropertyName( Method method )
  {
    String methodName = method.getName();
    return methodName.substring( 3 );
  }
}
