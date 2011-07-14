package cg.common.property;

/*
 * class property reflects the attributes/getter/setter of a class
 */
public class ClassProperty
{
  private String name;
  private Class<?> declaringClass;
  
  public ClassProperty(){}
  
  public ClassProperty( String name, Class<?> declaringClass )
  {
    setName( name );
    setDeclaringClass( declaringClass );
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public Class< ? > getDeclaringClass()
  {
    return declaringClass;
  }

  public void setDeclaringClass( Class< ? > declaringClass )
  {
    this.declaringClass = declaringClass;
  }

  public String getGetterMethodName()
  {
    return "get" + name.substring( 0, name.length() - 1 ).toUpperCase() + name.substring( 1 );
  }

  public String getSetterMethodName()
  {
    return "set" + name.substring( 0, name.length() - 1 ).toUpperCase() + name.substring( 1 );
  }

}
