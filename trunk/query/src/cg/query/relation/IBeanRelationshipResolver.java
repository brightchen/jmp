package cg.query.relation;

import java.util.Map;

public interface IBeanRelationshipResolver
{
  public BeanRelationship resolveRelationship( Map< String, Class<?> > aliasBeanMap );
}
