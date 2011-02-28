package cg.services.session.api;

//mark the session key is implemented by enum
public interface ISessionKey
{
  public long getNumberOfKeys();
  public int ordinal();   // take advantage of enum
  public String name();   // take advantage of enum
}
