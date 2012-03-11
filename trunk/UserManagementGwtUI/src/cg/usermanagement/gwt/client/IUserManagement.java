package cg.usermanagement.gwt.client;


import cg.gwt.components.shared.data.FrameData;
import cg.gwtui.client.IGwtUiManagement;
import cg.usermanagement.gwt.shared.data.SearchUserData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelOperation;
import cg.usermanagement.gwt.shared.data.UserRegisterData;
import cg.usermanagement.shared.LoginException;
import cg.usermanagement.shared.RegisterUserException;
import cg.usermanagement.shared.RoleException;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/*
 * for each request, return the response datas which required to build the next UI
 * by this way, the web server side can control the UI flow completely
 */
@RemoteServiceRelativePath( "UserManagement")
public interface IUserManagement extends IGwtUiManagement
{
  /*
   * return the information of Control Section UI and Client Section Start UI
   */
  public FrameData getStartUI( String localeName );
  
  public FrameData changeLocale( String localeName );
  
  public FrameData userlogin( String userName, String password ) throws LoginException;
  public FrameData accountlogin( String accountName, String password ) throws LoginException;
  
  public void registerUser( UserRegisterData data ) throws RegisterUserException;
  
  public long addRole( String roleName ) throws RoleException;
  
  public FrameData searchUser( SearchUserData searchUserData );
  
  public FrameData onUserManagementPanelOperation( UserManagementPanelOperation operation );
}
