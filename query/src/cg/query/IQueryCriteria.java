package cg.query;

import java.util.Map;

public interface IQueryCriteria
{
  /**
   * get the hsql for the criteria
   * for example sum( user1.age between ( 10, 20 )
   * @return: the generated hsql
   */
  public String getHsql();
  
  /*
   * parameter aliases map ( alias ==> beanClass )
   * use addAliases instead of getAliases can improve the performance and eliminate the memory frictions.
   */
  @SuppressWarnings( "rawtypes" )
  public void addAliases( Map< String, Class > aliases ); 
}
