package cg.common.property;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/*
 * class property reflects the attributes/getter/setter of a class
 */
public class ClassProperty
{
  private String name;
  private Class<?> declaringClass;
  private Class<?> propertyType;    //A for A< String, Integer >
  private Type[] typeArguments;     //[String, Integer] for A< String, Integer >
  
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

  public void setGenericType( Type genericType )
  {
    if( genericType instanceof ParameterizedType )
    {
      setPropertyType( (Class<?>)( (ParameterizedType)genericType).getOwnerType() );
      setTypeArguments( ((ParameterizedType)genericType).getActualTypeArguments() );
    }
    else if( genericType instanceof Class )
    {
      setPropertyType( (Class<?>)genericType );
    }
  }

  public Class< ? > getPropertyType()
  {
    return propertyType;
  }

  public void setPropertyType( Class< ? > propertyType )
  {
    this.propertyType = propertyType;
  }

  
  public Type[] getTypeArguments()
  {
    return typeArguments;
  }

  public void setTypeArguments( Type[] typeArguments )
  {
    this.typeArguments = typeArguments;
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
