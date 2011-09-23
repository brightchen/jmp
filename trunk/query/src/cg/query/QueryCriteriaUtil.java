package cg.query;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cg.common.property.ClassProperty;
import cg.common.property.ClassPropertyExt;
import cg.common.property.ClassPropertyUtil;
import cg.common.util.ConvertUtil;
import cg.common.util.ObjectUtil;

public class QueryCriteriaUtil
{
  /**
   * build the composite query criteria from the criteria and entity
   * @param entityClass: the class of the entity which can get the field of query criteria  
   * @param criteria: the criteria which can get the value of query criteria
   * @return: IQueryCriteria
   */
  public static IQueryCriteria buildEqualsCriteria( Class entityClass, Object criteria )
  {
    if( entityClass == null || criteria == null )
      return null;
    
    Set< ClassProperty > entityProperties = ClassPropertyUtil.getClassProperties( entityClass );
    Set< ClassProperty > criteriaProperties = ClassPropertyUtil.getClassProperties( criteria.getClass() );
    if( entityProperties == null || entityProperties.isEmpty() || criteriaProperties == null || criteriaProperties.isEmpty() )
      return null;
    
    List< IQueryCriteria > queryCriterias = new ArrayList< IQueryCriteria >();
    for( ClassProperty entityProperty : entityProperties )
    {
      ClassProperty criteriaProperty = getCompatibleProperty( entityProperty, criteriaProperties );
      if( criteriaProperty == null)
        continue;
      
      //build the query criteria for this property
      queryCriterias.add( buildEqualCriteria( entityProperty, criteriaProperty, criteria ) );
    }
    return CompositeQueryCriteria.buildQuery( CriteriaOperator.And, queryCriterias.toArray( new IQueryCriteria[ queryCriterias.size() ] ) );
  }
  
  public static IQueryCriteria buildEqualCriteria( ClassProperty entityProperty, ClassProperty criteriaProperty, Object criteria )
  {
    ClassPropertyExt criteriaPropertyExt = ClassPropertyUtil.toClassPropertyExt( criteriaProperty );
  }
  
  /**
   * get the property form properties which compatible with compareProperty( same name, type compatible )
   * @param compareProperty: the comparing property 
   * @param properties:
   * @return: the property meet the criteria, return null if no property found
   */
  public static ClassProperty getCompatibleProperty( ClassProperty compareProperty, Set< ClassProperty > properties )
  {
    if( properties == null || properties.isEmpty() )
      return null;
    final String propertyName = compareProperty.getName();
    final Class rawType = compareProperty.getPropertyRawType();
    final Type[] typeArguments = compareProperty.getTypeArguments();
    
    for( ClassProperty property : properties )
    {
      if( !propertyName.equals( property.getName() ) )
        continue;
      if( !ConvertUtil.isConvertable( property.getPropertyRawType(), compareProperty ) )
        continue;
      
      Type[] theTypeArguments = property.getTypeArguments();
      if( ObjectUtil.equals( typeArguments, theTypeArguments ) )
      {
        return property;
      }
    }
    return null;
  }
}
