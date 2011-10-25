package cg.query;

public interface IQueryCriteriaBuilder
{
  /**
   * build the composite query criteria from the criteria and entity
   * @param entityClass: the class of the entity which can get the field of query criteria  
   * @param criteria: the criteria which can get the value of query criteria
   * @return: IQueryCriteria
   */
  @SuppressWarnings( { "rawtypes" } )
  public IQueryCriteria buildEqualsCriteria( Class entityClass, Object criteria );
  
  /**
   * build the composite query criteria from the criteria and entity
   * @param entityClass: the class of the entity which can get the field of query criteria  
   * @param criteria: the criteria which can get the value of query criteria
   * @param operator: the operator of the query, it must be only parameter operator
   * @return: IQueryCriteria
   */
  public IQueryCriteria buildCriteria( Class entityClass, Object criteria, Operator operator );

}
