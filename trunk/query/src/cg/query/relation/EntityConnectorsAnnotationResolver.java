package cg.query.relation;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import cg.common.property.ClassProperty;
import cg.common.property.ClassPropertyExt;
import cg.common.property.ClassPropertyUtil;

/**
 * resolve the entity connectors by parsing the annotations
 * @author bchen
 * 
 */
@SuppressWarnings( "rawtypes" )
public class EntityConnectorsAnnotationResolver extends EntityConnectorAbstractResolver
{
  private static EntityConnectorsAnnotationResolver defaultInstance;
  
  public static EntityConnectorsAnnotationResolver defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( EntityConnectorsAnnotationResolver.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new EntityConnectorsAnnotationResolver();
        }
      }
    }
    return defaultInstance;
  }

  private EntityConnectorsAnnotationResolver(){}
  

  /**
   * get connectors( entity, property ) which directly connected to the entity
   * @param entity
   * @return the list of connectors which directly connected to the entity 
   */
  @Override
  public Set< EntityConnector > getDirectConnectors( Class entity )
  {
    Set< EntityConnector > entityConnectors = new HashSet< EntityConnector >();
    
    //check all the relationship annotations
    Set< ClassProperty >  properties = ClassPropertyUtil.getClassProperties( entity );
    if( properties == null || properties.isEmpty() )
    {
      //better to throw exception?
      return Collections.emptySet();
    }
    
    for( ClassProperty property : properties )
    {
      ClassPropertyExt propertyExt = ClassPropertyUtil.toClassPropertyExt( property );
      Field field = propertyExt.getField();
      
      // resolve ManyToOne relationship
      ManyToOne manyToOne = field.getAnnotation( ManyToOne.class );
      if( manyToOne != null )
      {
        //this property connected to field type's id property
        entityConnectors.add( new EntityConnector( property, getIdProperty( field.getType() ) ) );
      }
      
      //resolve ManyToMany relationship
      ManyToMany manyToMany = field.getAnnotation( ManyToMany.class );
      if( manyToMany != null )
      {
        //get the type of another entity
        Class targetEntity = manyToMany.targetEntity();
        if( targetEntity == null )
        {
          //this field should a collection
          Class<?> fieldType = field.getType();
          if( !Collection.class.isAssignableFrom( fieldType ) )
          {
            throw new RuntimeException( "the field type of ManyToMany should be a Collection." );
          }
          
          //get the type of collection
          if( !Collection.class.isAssignableFrom( property.getPropertyRawType() ) )
          {
            //it should be error
          }
          Type[] typeArguments = property.getTypeArguments();
          if( typeArguments == null || typeArguments.length != 1 )
          {
            //error
          }
          entityConnectors.add( new EntityConnector( property, getIdProperty( (Class)typeArguments[0] ) ) );
        }
      }
      
      //should we resolve OneToMany relationship??
    }
    
    return entityConnectors;
  
  }


  protected ClassProperty getIdProperty( Class< ? > entityClass )
  {
    ClassProperty property = new ClassProperty();
    property.setDeclaringClass( entityClass );
    property.setName( "id" );
    property.setPropertyRawType( Long.class );
    return property;
  }
}
