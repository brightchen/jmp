package cg.query.relation;

import java.util.ArrayList;
import java.util.List;

public class BeanRelationship
{
  // beans which are connected by the beanLine
  private List< BeanConnector > beanLine = new ArrayList< BeanConnector >();
  
  public String toSqlRelationshipClause()
  {
    return null;
  }
  
  public List< BeanConnector > getBeanLine()
  {
    return beanLine;
  }
  
  public void addConnector( BeanConnector beanConnector )
  {
    beanLine.add( beanConnector );
  }
  
}
