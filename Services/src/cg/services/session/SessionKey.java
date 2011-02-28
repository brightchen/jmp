package cg.services.session;

import cg.services.session.api.ISessionKey;

public enum SessionKey implements ISessionKey
{
  sessionId;

  @Override
  public long getNumberOfKeys()
  {
    return SessionKey.values().length;
  }
  
  
}
