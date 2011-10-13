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
import cg.common.util.StringUtil;

public class QueryCriteriaBuilder implements IQueryCriteriaBuilder
{
  private static QueryCriteriaBuilder defaultInstance;
  
  public static QueryCriteriaBuilder defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( QueryCriteriaBuilder.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new QueryCriteriaBuilder();
        }
      }
    }
    return defaultInstance;
  }
  
  private QueryCriteriaBuilder(){}
  
  /**
   * build the composite query criteria from the criteria and entity
   * @param entityClass: the class of the entity which can get the field of query criteria  
   * @param criteria: the criteria which can get the value of query criteria
   * @return: IQueryCriteria
   */
  @Override
  @SuppressWarnings( { "rawtypes" } )  
  public IQueryCriteria buildEqualsCriteria( Class entityClass, Object criteria )
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
      //build the query criteria for this property
      IQueryCriteria queryCriteria = buildEqualCriteria( entityProperty, criteriaProperty, criteria );
      if( queryCriteria != null )
        queryCriterias.add( queryCriteria );
    }
    final int queryCriteriasSize = queryCriterias.size();
    if( queryCriteriasSize == 0)
    {
      //no criteria, return empty criteria
      EmptyQueryCriteria.instance();
    }
    if( queryCriteriasSize == 1 )
    {
      //return simple criteria
      return queryCriterias.get( 0 );
    }
    
    return CompositeQueryCriteria.buildQuery( CriteriaOperator.And, queryCriterias.toArray( new IQueryCriteria[ queryCriterias.size() ] ) );
  }
  
  /**
   * build the equal criteria for an property
   * @param entityProperty: the entity property
   * @param criteriaProperty: the criteria property
   * @param criteria: the criteria which can get the criteria value
   * @return
   */
  public IQueryCriteria buildEqualCriteria( ClassProperty entityProperty, ClassProperty criteriaProperty, Object criteria )
  {
    if( criteriaProperty == null )
      return null;
    ClassPropertyExt criteriaPropertyExt = ClassPropertyUtil.toClassPropertyExt( criteriaProperty );
    Object propertyValue = criteriaPropertyExt.getPropertyValue( criteria );
    if( isIgnoreProperty( criteriaProperty, propertyValue ) )
      return null;  // propertyValue equals means not need to care this property;
    return new QueryCriteria( entityProperty, Operator.Equal, propertyValue );
  }
  
  /**
   * should the system ignore this property when 
   * @param criteriaProperty
   * @param propertyValue
   * @return return true if this property/value should be ignored
   */
  protected boolean isIgnoreProperty( ClassProperty criteriaProperty, Object propertyValue )
  {
    if( propertyValue == null )
      return true;
    if( ( propertyValue instanceof String ) && StringUtil.isNullOrEmpty( (String)propertyValue ) )
    {
      return true;
    }
    
    return false;
  }
  
  /**
   * get the property form properties which compatible with compareProperty( same name, type compatible )
   * @param compareProperty: the comparing property 
   * @param properties:
   * @return: the property meet the criteria, return null if no property found
   */
  public ClassProperty getCompatibleProperty( ClassProperty compareProperty, Set< ClassProperty > properties )
  {
    if( properties == null || properties.isEmpty() )
      return null;
    final String propertyName = compareProperty.getName();
    final Type[] typeArguments = compareProperty.getTypeArguments();
    
    for( ClassProperty property : properties )
    {
      if( !propertyName.equals( property.getName() ) )
        continue;
      //check raw type
      if( !ConvertUtil.isConvertable( property.getPropertyRawType(), compareProperty.getPropertyRawType() ) )
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