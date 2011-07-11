package cg.usermanagement.gwt.shared.data;

import java.io.Serializable;

import cg.gwt.components.shared.data.UIResourceData;
import cg.usermanagement.gwt.server.resource.UserManagementResourceKey;

public class UserManagementResourceData extends UIResourceData implements Serializable
{
  private static final long serialVersionUID = -7767903569477317370L;

  //?? we should get rid of the Resource data depended on the UserManagementResourceKey as it fatted the client source code
  public static void addPropertyResource( String propertyName, UserManagementResourceKey resourceKey )
  {
    addPropertyResource( propertyName, resourceKey.getStringKey() );
  }

}
