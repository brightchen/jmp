package cg.query.relation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import cg.common.property.ClassProperty;

@SuppressWarnings( "rawtypes")
public class EntityRelationUtil
{
  /*
   * get the direct connected entities of entity 
   */
  public static Set< Class > getDirectConnectedEntities( Class entity, Set< EntityConnector > connectors )
  {
    if( connectors == null || connectors.isEmpty() )
      return Collections.emptySet();
    
    Set< Class > entities = new HashSet< Class >();
    for( EntityConnector connector : connectors )
    {
      ClassProperty otherProperty = connector.getPropertyOfAnotherEntity( entity );
      if( otherProperty == null )
        continue;
      entities.add( otherProperty.getDeclaringClass() );
    }
    return entities;
  }
}
