package cg.query.relation;

import cg.common.property.ClassProperty;

/*
 * entity1 connect to entity2 via BeanConnector
 * the beanPropty1 equals beanPropty2 in terms of sql
 * for example account.user and user.id
 */
public class EntityConnector
{
  private ClassProperty entityProperty1;
  private ClassProperty entityProperty2;
  
  public EntityConnector(){}
  
  public EntityConnector( ClassProperty entityPropty1, ClassProperty entityPropty2 )
  {
    setEntityProperty1( entityPropty1 );
    setEntityProperty2( entityPropty2 );
  }

  public ClassProperty getEntityProperty1()
  {
    return entityProperty1;
  }

  public void setEntityProperty1( ClassProperty entityProperty1 )
  {
    this.entityProperty1 = entityProperty1;
  }

  public ClassProperty getEntityProperty2()
  {
    return entityProperty2;
  }

  public void setEntityProperty2( ClassProperty entityProperty2 )
  {
    this.entityProperty2 = entityProperty2;
  }
  
  public ClassProperty getPropertyOfEntity( Class<?> entity )
  {
    if( entity.equals( entityProperty1.getDeclaringClass() ) )
      return entityProperty1;
    if( entity.equals( entityProperty2.getDeclaringClass() ) )
      return entityProperty2;
    return null;
  }

  public ClassProperty getPropertyOfOtherEntity( Class<?> entity )
  {
    if( entity.equals( entityProperty1.getDeclaringClass() ) )
      return entityProperty2;
    if( entity.equals( entityProperty2.getDeclaringClass() ) )
      return entityProperty1;
    throw new RuntimeException( "No entity of connctor equals to input entity" );
  }

}
