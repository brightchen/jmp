package cg.query;

/*
 * this class provides service to build query according to the QueryCriteria
 */
public class SmartQueryBuilder
{
  public <E> String buildSearchHsql( Class< E > objectiveClass, QueryCriteria criteria )
  {
    final String objectiveAlias = getAlias( objectiveClass );
    return "select " + objectiveAlias + " from " + buildAliasList( objectiveClass, criteria ) 
        + " where " + buildRelationClause( objectiveClass, criteria ) + buildCriteriaClause( criteria );
  }
  
  public String getAlias( Class<?> entityClass )
  {
    String className = entityClass.getSimpleName();
    return className.substring( 0, 1 ).toLowerCase() + className.substring( 1 );
  }
  
  /*
   * build the alias list of search query
   */
  public <E> String buildAliasList( Class< E > objectiveClass, QueryCriteria criteria )
  {
    
  }
}
