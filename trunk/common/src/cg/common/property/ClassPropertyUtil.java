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
  
  /*
   * get properties from class clazz and its super classes until rootSuperClass
   */
  public static <T> Set< IClassProperty > getClassProperties( Class<T> clazz, Class< ? super T > rootSuperClass )
  {
    Set< IClassProperty > properties = new HashSet< IClassProperty >();
    for( Class< ? super T > superClass = clazz; superClass != null && !superClass.equals( rootSuperClass ); superClass = superClass.getSuperclass()  )
    {
      properties.addAll( getClassPropertiesFlattly( superClass ) );
    }
    
    return null;
  }
  
  /*
   * get the properties for this class only, it doesn't go through the class hierarchy
   */
  public static <T> getClassPropertiesFlattly( Class<T> clazz )
  {
    
  }
}
