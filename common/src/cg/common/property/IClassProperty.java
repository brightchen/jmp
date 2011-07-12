package cg.common.property;

/*
 * class property reflects the attributes/getter/setter of a class
 */
public interface IClassProperty
{
  public String name();
  public Class<?> getDeclaringClass();
}
