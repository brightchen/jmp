package cg.query.relation;

import java.util.List;
import java.util.Map;

public interface IEntityRelationshipResolver
{
  /*
   * get the connectors( entity, property ) which directly connected to the entity 
   */
  public List< EntityConnector > getDirectConnectors( Class<?> entity );
  
  public EntityRelationship resolveRelationship( Map< String, Class<?> > aliasBeanMap );
}
