package cg.query.relation;

import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings( "rawtypes" )
public interface IEntityConnectorsResolver
{
  /**
   * get connectors( entity, property ) which directly connected to the entity
   * @param entity
   * @return the list of connectors which directly connected to the entity 
   */
  public List< EntityConnector > getDirectConnectors( Class entity );

  /**
   * 
   * @param entities: the entities which need to get the connectors
   * @return: a map of entity to its directly connectors
   */
  public Map< Class, Set< EntityConnector > > getDirectConnectors( Set< Class > entities );
}
