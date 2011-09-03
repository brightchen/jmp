package cg.common.property;

/*
 * class property reflects the attributes/getter/setter of a class
 */
public class ClassProperty
{
  private String name;
  private Class<?> declaringClass;
  private Class<?> propertyType;
  
  public ClassProperty(){}
  
  public ClassProperty( String name, Class<?> declaringClass, Class<?> propertyType )
  {
    setName( name );
    setDeclaringClass( declaringClass );
    setPropertyType( propertyType );
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

  public Class< ? > getPropertyType()
  {
    return propertyType;
  }

  public void setPropertyType( Class< ? > propertyType )
  {
    this.propertyType = propertyType;
  }

  @Override
  public boolean equals( Object obj )
  {
    if( obj == null )
      return false;
    
    if( obj == this )
      return true;
    
    if( !( obj instanceof ClassProperty ) )
      return false;
    ClassProperty otherProperty = ( ClassProperty )obj;
    
    return equals( otherProperty.name, name ) && equals( otherProperty.declaringClass, declaringClass );
  }
  
  protected static boolean equals( Object obj1, Object obj2 )
  {
    if( obj1 == obj2 )
      return true;
    if( obj1 == null || obj2 == null )
      return false;
    return obj1.equals( obj2 );
  }
}
