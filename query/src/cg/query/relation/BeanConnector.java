package cg.query.relation;

import cg.common.property.ClassProperty;

/*
 * bean1 connect to bean2 via BeanConnector
 * the beanPropty1 equals beanPropty2 in terms of sql
 * for example account.user and user.id
 */
public class BeanConnector
{
  private ClassProperty beanPropty1;
  private ClassProperty beanPropty2;
  
  public BeanConnector(){}
  
  public BeanConnector( ClassProperty beanPropty1, ClassProperty beanPropty2 )
  {
    setBeanPropty1( beanPropty1 );
    setBeanPropty2( beanPropty2 );
  }

  public ClassProperty getBeanPropty1()
  {
    return beanPropty1;
  }

  public void setBeanPropty1( ClassProperty beanPropty1 )
  {
    this.beanPropty1 = beanPropty1;
  }

  public ClassProperty getBeanPropty2()
  {
    return beanPropty2;
  }

  public void setBeanPropty2( ClassProperty beanPropty2 )
  {
    this.beanPropty2 = beanPropty2;
  }
  
}
