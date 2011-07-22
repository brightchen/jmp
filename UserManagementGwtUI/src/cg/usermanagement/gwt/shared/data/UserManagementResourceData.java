package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.ResourceData;

/*
 * this class is the super class of ResourceData class of UserManagement module
 * we should get rid of the Resource data depended on the UserManagementResourceKey as it fatted the client source code
 */
public class UserManagementResourceData extends ResourceData implements Serializable
{
  private static final long serialVersionUID = -7767903569477317370L;
}
