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

}
