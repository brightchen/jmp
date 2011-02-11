package cg.common.identity;

public interface Identifiable< T extends Comparable<T> >
{
  public T getIdentity();
}
