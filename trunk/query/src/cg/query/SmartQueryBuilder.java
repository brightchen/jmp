package cg.query;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cg.common.property.ClassProperty;
import cg.query.relation.EntityConnector;
import cg.query.relation.EntityNetwork;
import cg.query.relation.EntityNetworkManager;

/*
 * this class provides service to build query according to the QueryCriteria
 */
public class SmartQueryBuilder
{
  /*
   * ObjectiveClass: the class of the entity which we want to retrieve
   * criteria: the criteria for the query
   */
  public <E> String buildSearchHsql( Class< ? > objectiveClass, IQueryCriteria criteria )
  {
    final String objectiveAlias = getAlias( objectiveClass );
    Map< String, Class<?> > aliasMap = buildAliasMap( criteria );
    
    return "select " + objectiveAlias + " from " + buildAliasList( aliasMap )
        + " where " + buildRelationClause( getAllEntityAliasMap( aliasMap, objectiveAlias, objectiveClass ) ) 
        + buildCriteriaClause( criteria );
  }
  
  
  public String getAlias( Class entity )
  {
    String simpleName = entity.getSimpleName();
    return simpleName.substring( 0, 1 ).toLowerCase() + simpleName.substring( 1 );
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

  protected Map< String, Class<?> > getAllEntityAliasMap( Map< String, Class<?> > aliasMap, String objectiveAlias, Class<?> objectiveBean )
  {
    aliasMap.put( objectiveAlias, objectiveBean );
    return aliasMap;
  }
  /*
   * the relationship between the entity classes
   * parameter: aliasBeanMap the map ( alias ==> entity class )
   * for example role.account = account.id and account.user = user.id 
   */
  public String buildRelationClause( Map< String, Class<?> > aliasEntityMap )
  {
    EntityNetwork network = EntityNetworkManager.defaultInstance().resolveNetwork( aliasEntityMap );
    Set< EntityConnector > connectors = network.getConnectors();
    if( connectors == null || connectors.isEmpty() )
      return "";
    StringBuilder relationClause = new StringBuilder();
    for( EntityConnector connector : connectors )
    {
      relationClause.append( buildRelationClause( connector ) ).append( " and " );
    }
    return relationClause.toString();
  }
  
  /**
   * build the relation clause for the connector
   * @param connector: the connector which the relation clause corresponding to
   * @return
   */
  public String buildRelationClause( EntityConnector connector )
  {
    if( connector == null )
      return "";
    
    StringBuilder relationClause = new StringBuilder();
    
    {
      ClassProperty property1 = connector.getEntityProperty1();
      relationClause.append(  getAlias( property1.getDeclaringClass() ) + "." + property1.getName() );
    }
    {
      ClassProperty property2 = connector.getEntityProperty2();
      relationClause.append(  getAlias( property2.getDeclaringClass() ) + "." + property2.getName() );
    }
    return relationClause.toString();
  }
  
  /**
   * build the sql criteria clause according to IQueryCriteria
   * @param criteria
   * @return
   */
  public String buildCriteriaClause( IQueryCriteria criteria )
  {
    return criteria.getHsql();
  }
}
