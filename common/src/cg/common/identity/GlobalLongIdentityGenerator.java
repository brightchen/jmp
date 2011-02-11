package cg.common.identity;

public class GlobalLongIdentityGenerator
{
  private static long currentIdentity = Math.round( Math.random() );
  public synchronized static long generateIdentity()
  {
    return ++currentIdentity;
  }
}
