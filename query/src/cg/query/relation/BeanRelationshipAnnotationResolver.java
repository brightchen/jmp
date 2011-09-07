package cg.query.relation;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.ManyToOne;

import cg.common.property.ClassProperty;
import cg.common.property.ClassPropertyExt;
import cg.common.property.ClassPropertyUtil;

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
          BeanNetwork.instance().addConnector( new BeanConnector( property, field.getType() ) )
        }
        field.getAnnotations();
        //++++
      }
      
    }
    
    //then get the best line from the graphic
  }

}
