package cg.query;

import java.util.List;

import cg.common.property.ClassProperty;


public class QueryCriteria<E> implements IQueryCriteria
{
  private String beanAlias;
  private ClassProperty property;
  private Operator operator;
  private List<Object> parameters;
  
  @Override
  public String getHsql()
  {
    String hsql = String.format( "( %s.%s %s %s )", beanAlias, property.getName(), operator.getKeyword(), getParameterList() );
    return hsql;
  }

  /*
   * need to get the type of property in order to determine if should add quote
   */
  public String getParameterList()
  {
    if( NumberOfParameter.One.equals( operator.getNumberOfParameter() ) )
    {
      return parameters[0];
    }
  }
  
  public String getBeanAlias()
  {
    return beanAlias;
  }


  public void setBeanAlias( String beanAlias )
  {
    this.beanAlias = beanAlias;
  }


  public ClassProperty getProperty()
  {
    return property;
  }

  public void setProperty( ClassProperty property )
  {
    this.property = property;
  }

  public Operator getOperator()
  {
    return operator;
  }

  public void setOperator( Operator operator )
  {
    this.operator = operator;
  }

  public List< Object > getParameters()
  {
    return parameters;
  }

  public void setParameters( List< Object > parameters )
  {
    this.parameters = parameters;
  }
  
  
}
