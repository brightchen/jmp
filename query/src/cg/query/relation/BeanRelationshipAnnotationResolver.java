package cg.query.relation;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import cg.common.property.ClassProperty;
import cg.common.property.ClassPropertyExt;
import cg.common.property.ClassPropertyUtil;
import cg.model.api.IEntity;

/*
 * resolve the relationship by bean annotation
 */
public class BeanRelationshipAnnotationResolver implements IBeanRelationshipResolver
{
  private static BeanRelationshipAnnotationResolver defaultInstance;
  
  public static BeanRelationshipAnnotationResolver defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( BeanRelationshipAnnotationResolver.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new BeanRelationshipAnnotationResolver();
        }
      }
    }
    return defaultInstance;
  }

  private BeanRelationshipAnnotationResolver(){}
  

  @Override
  public BeanRelationship resolveRelationship( Map< String, Class< ? >> aliasBeanMap )
  {
    //build the graphic
    //when resolve the relationship via annotation, we hard to tell the bean with different alias ( living/mail address )
    Set< Class<?> > beanClasses = new HashSet< Class<?> >();
    for( Map.Entry< String, Class<?> > entry : aliasBeanMap.entrySet() )
    {
      beanClasses.add( entry.getValue() );
    }
    
    for( Class<?> beanClass : beanClasses )
    {
      //check all the relationship annotations
      Set< ClassProperty >  properties = ClassPropertyUtil.getClassProperties( beanClass );
      if( properties == null || properties.isEmpty() )
      {
        //better to throw exception?
        continue;
      }
      
      for( ClassProperty property : properties )
      {
        ClassPropertyExt propertyExt = ClassPropertyUtil.toClassPropertyExt( property );
        Field field = propertyExt.getField();
        
        ManyToOne manyToOne = field.getAnnotation( ManyToOne.class );
        if( manyToOne != null )
        {
          //this property connected to field type's id property
          BeanNetwork.instance().addConnector( new BeanConnector( property, getIdProperty( field.getType() ) ) );
        }
        
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
            TypeVariable<Class<T>>[] fieldType.getTypeParameters();
          }
        }
      }
      
    }
    
    //then get the best line from the graphic
  }

  protected ClassProperty getIdProperty( Class< ? > entityClass )
  {
    ClassProperty property = new ClassProperty();
    property.setDeclaringClass( entityClass );
    property.setName( "id" );
    property.setPropertyType( Long.class );
    return property;
  }
}
