package cg.common.identity;

public class GlobalLongIdentityGenerator
{
  private static long currentIdentity = Math.round( Math.random() );
  private static final long MAX_STEP_LENGTH = 100;   //increase the MAX_STEP_LENGTH decrease the chance of hack
  public synchronized static long generateIdentity()
  {
    currentIdentity += Math.round( Math.random() ) % MAX_STEP_LENGTH;
    return currentIdentity;
  }
}
