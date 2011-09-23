package cg.common.property;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassPropertyExt extends ClassProperty
{
  private Method getter;
  private Method setter;
  private Field field;
  
  public ClassPropertyExt(){}
  
  /**
   * get the property value
   * @param object: the class instance this property belongs to
   */
  public Object getPropertyValue( Object object )
  {
    if( !object.getClass().isAssignableFrom( getDeclaringClass()  ) )
    {
      throw new IllegalArgumentException( "The object should be the instanceof " + getDeclaringClass().getName() );
    }
    
    // get the null getter/setter/field and get value
  }
  
  public boolean valueHasSet()
  {
    return ( getter != null || setter != null || field != null );
  }
  
  public Method getGetter()
  {
    return getter;
  }
  public void setGetter( Method getter )
  {
    this.getter = getter;
  }
  public Method getSetter()
  {
    return setter;
  }
  public void setSetter( Method setter )
  {
    this.setter = setter;
  }
  public Field getField()
  {
    return field;
  }
  public void setField( Field field )
  {
    this.field = field;
  }
  
  
}
