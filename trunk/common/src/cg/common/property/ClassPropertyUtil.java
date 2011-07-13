package cg.common.property;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

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
  
  public static <T> Set< IClassProperty > getClassProperties( Class<T> clazz, Class< ? super T > rootSuperClass )
  {
    return getClassProperties( clazz, rootSuperClass, defaultPropertyCriteria );
  }
  
  /*
   * get properties from class clazz and its super classes until rootSuperClass
   */
  public static <T> Set< IClassProperty > getClassProperties( Class<T> clazz, Class< ? super T > rootSuperClass, PropertyCriteria criteria )
  {
    Set< IClassProperty > properties = new HashSet< IClassProperty >();
    for( Class< ? super T > superClass = clazz; superClass != null && !superClass.equals( rootSuperClass ); superClass = superClass.getSuperclass()  )
    {
      properties.addAll( getClassPropertiesFlattly( superClass, criteria ) );
    }
    
    return null;
  }
  
  /*
   * get the properties for this class only, it doesn't go through the class hierarchy
   */
  public static <T> Set< IClassProperty > getClassPropertiesFlattly( Class<T> clazz, PropertyCriteria criteria )
  {
    return null;
  }
  
  public static <T> Set< IClassProperty > getClassGetterPropertiesFlattly( Class<T> clazz )
  {
    ReflectionUtil.ge
  }

  public static <T> Set< IClassProperty > getClassSetterPropertiesFlattly( Class<T> clazz )
  {
    
  }

}
