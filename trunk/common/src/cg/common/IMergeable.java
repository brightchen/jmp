package cg.common;

public interface IMergeable< T >
{
  // merge this with merging, the merging could be the sub or the super of this
  // return the merged, usually this
  public <I extends T> void merge( I merging );
}
