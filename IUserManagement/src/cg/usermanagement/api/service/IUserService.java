package cg.usermanagement.api.service;

import cg.usermanagement.api.model.IUser;

public interface IUserService
{
  public IUser findUserByName( String name );
}
