package cg.query;

import java.util.Map;

public interface IQueryCriteria
{
  public String getHsql();
  
  /*
   * parameter aliases map ( alias ==> beanClass )
   * use addAliases instead of getAliases can improve the performance and eliminate the memory frictions.
   */
  public void addAliases( Map< String, Class > aliases ); 
}
