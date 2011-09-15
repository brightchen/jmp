package cg.query.relation;

import java.util.List;
import java.util.Set;

public interface IEntityRelationshipResolver
{
  /*
   * get the connectors( entity, property ) which directly connected to the entity 
   */
  public List< EntityConnector > getDirectConnectors( Class<?> entity );
  
  /*
   * build the network for entities
   */
  public boolean buildNetwork( EntityNetwork entityNetwork, Set< Class > entities );
}
