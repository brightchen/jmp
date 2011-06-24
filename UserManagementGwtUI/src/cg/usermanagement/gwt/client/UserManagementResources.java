package cg.usermanagement.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface UserManagementResources extends ClientBundle
{
  public static final UserManagementResources INSTANCE = GWT.create( UserManagementResources.class );
  
  @Source( "UserManagement.properties" )
  public TextResource userManagement();
}
