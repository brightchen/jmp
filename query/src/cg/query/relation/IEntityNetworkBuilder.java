package cg.query.relation;

import java.util.List;
import java.util.Set;

public interface IEntityNetworkBuilder
{
  /**
   * get connectors( entity, property ) which directly connected to the entity
   * @param entity
   * @return the list of connectors which directly connected to the entity 
   */
  public List< EntityConnector > getDirectConnectors( Class entity );

  /**
   * build the entity network for entities
   * @param entityNetwork: the entity network which entities going to merge
   * @param entities: the entities of which going to merge into the network
   * @return true if build network successful
   */
  public boolean buildNetwork( EntityNetwork entityNetwork, Set< Class > entities );
}
