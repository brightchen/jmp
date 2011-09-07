package cg.query.relation;

import java.util.ArrayList;
import java.util.List;

public class BeanRelationship
{
  // beanRoute is a route from the start bean to the end bean
  private List< BeanConnector > beanRoute = new ArrayList< BeanConnector >();
  
  public String toSqlRelationshipClause()
  {
    return null;
  }
  
  public List< BeanConnector > getBeanRoute()
  {
    return beanRoute;
  }
  
  public void addConnector( BeanConnector beanConnector )
  {
    beanRoute.add( beanConnector );
  }
  
}
