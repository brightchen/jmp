package cg.query;

import java.util.List;

import cg.common.property.ClassProperty;
import cg.common.util.StringUtil;


public class QueryCriteria<E> implements IQueryCriteria
{
  private String beanAlias;
  private ClassProperty property;
  private Operator operator;
  private List<Object> parameters;
  
  @Override
  public String getHsql()
  {
    String hsql = String.format( "( %s.%s %s %s )", beanAlias, property.getName(), operator.getKeyword(), getSqlParameterList() );
    return hsql;
  }

  /*
   * need to get the type of property in order to determine if should add quote
   */
  public String getSqlParameterList()
  {
    if( NumberOfParameter.One.equals( operator.getNumberOfParameter() ) )
    {
      return getSqlParameter( parameters.get( 0 ) );
    }
    
    
    {
      StringBuilder sqlParameter = new StringBuilder( "( " );
      for( Object parameter : parameters )
      {
        sqlParameter.append( getSqlParameter( parameter ) + ", " );
      }
      sqlParameter.setCharAt( sqlParameter.length() - 2, ')' ); //replace the last ',' to ')'
      
      return sqlParameter.toString();
    }
  }

  protected String getSqlParameter( Object parameter )
  {
    String parameterString = StringUtil.toString( parameter );
    return String.class.equals( property.getPropertyType() ) ? "'" + parameterString + "'" : parameterString;
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
