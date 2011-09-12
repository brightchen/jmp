package cg.query;

import java.util.HashMap;
import java.util.Map;

/*
 * this class provides service to build query according to the QueryCriteria
 */
public class SmartQueryBuilder
{
  /*
   * ObjectiveClass: the class of the bean which we want to retrieve
   * criteria: the criteria for the query
   */
  public <E> String buildSearchHsql( Class< ? > objectiveClass, IQueryCriteria criteria )
  {
    final String objectiveAlias = getAlias( objectiveClass );
    Map< String, Class<?> > aliasMap = buildAliasMap( criteria );
    
    return "select " + objectiveAlias + " from " + buildAliasList( aliasMap )
        + " where " + buildRelationClause( getAllBeanAliasMap( aliasMap, objectiveAlias, objectiveClass ) ) 
        + buildCriteriaClause( criteria );
  }
  
  public String getAlias( Class<?> entityClass )
  {
    String className = entityClass.getSimpleName();
    return className.substring( 0, 1 ).toLowerCase() + className.substring( 1 );
  }

  
  /*
   * build the alias list of search query
   * for example: User user, Account account, Role role
   */
  public String buildAliasList( IQueryCriteria criteria )
  {
    return buildAliasList( buildAliasMap( criteria ) );
  }
  
  protected Map< String, Class<?> > buildAliasMap( IQueryCriteria criteria )
  {
    Map< String, Class<?> > aliasMap = new HashMap< String, Class<?> >();
    criteria.addAliases( aliasMap );
    return aliasMap;
  }
  
  protected String buildAliasList( Map< String, Class<?> > aliasMap )
  {
    StringBuilder aliasesList = new StringBuilder();
    for( Map.Entry< String, Class<?> > entry : aliasMap.entrySet() )
    {
      aliasesList.append( String.format( "%s %s, ", entry.getValue().getName(), entry.getKey() ) );
    }
    
    //remove the last ", " 
    return aliasesList.substring( 0, aliasesList.length() - 2 );
  }

  protected Map< String, Class<?> > getAllBeanAliasMap( Map< String, Class<?> > aliasMap, String objectiveAlias, Class<?> objectiveBean )
  {
    aliasMap.put( objectiveAlias, objectiveBean );
    return aliasMap;
  }
  /*
   * the relationship between the entity classes
   * parameter: aliasBeanMap the map ( alias ==> bean class )
   * for example role.account = account.id and account.user = user.id 
   */
  public String buildRelationClause( Map< String, Class<?> > aliasBeanMap )
  {
    return EntityManager.defaultInstance().resolveRelationship( aliasBeanMap ).toSqlRelationshipClause();
  }
}
