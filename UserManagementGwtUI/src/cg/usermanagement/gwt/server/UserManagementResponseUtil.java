package cg.usermanagement.gwt.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cg.contentdata.management.ResourceDataManager;
import cg.gwt.components.shared.data.ResponseData;
import cg.gwt.components.shared.data.UIIdentity;
import cg.services.session.SessionManager;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.ListUsersData;
import cg.usermanagement.gwt.shared.data.UserListData;
import cg.usermanagement.gwt.shared.data.UserLoginData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelData;
import cg.usermanagement.gwt.shared.data.UserManagementStartData;
import cg.usermanagement.gwt.shared.data.UserRegisterData;

public class UserManagementResponseUtil extends ResponseUtil
{
  
  public static List< ResponseData<?> > buildUserManagementStartUI( Locale locale )
  {
    SessionManager.startSession();

    List< ResponseData<?> > rds = new ArrayList< ResponseData<?> >();
    
    // control section data
    {
      rds.add( buildControlSectionData( locale ) );
    }
    
    // user management data
    {
      ResponseData< UserManagementStartData > rd = new ResponseData< UserManagementStartData >();
      rd.setFlowData( UIIdentity.UM_START );

      UserLoginData userLoginData = new UserLoginData();
//      userLoginData.setResourceData( UserManagementResourceDataBuilder.buildUserLoginResourceData( locale ) );
      
      AccountLoginData accountLoginData = new AccountLoginData();
//      accountLoginData.setResourceData( UserManagementResourceDataBuilder.buildAccountLoginResourceData( locale ) );
      
      UserRegisterData userRegisterData = new UserRegisterData();
      //set resource data later
      //userRegisterData.setResourceData( UserManagementResourceDataBuilder.b() );
      
      UserManagementStartData data = new UserManagementStartData( userLoginData, accountLoginData, userRegisterData );
      ResourceDataManager.defaultInstance.injectResourceDatas( locale, data, true );
      rd.setContentData( data );
      
      rds.add( rd );
    }    
    return rds;
  }
  
  

  /*
   * get the response data to let the client build User Management Panel
   */
  public static List< ResponseData<?> > getUserManagementPanelDatas( Locale locale )
  {
    List< ResponseData<?> > rds = new ArrayList< ResponseData<?> >();
    
    // UM_CONTROL_PANEL
    {
      ResponseData< UserManagementPanelData > rd = new ResponseData< UserManagementPanelData >();
      rd.setFlowData( UIIdentity.UM_CONTROL_PANEL );

      UserManagementPanelData data = new UserManagementPanelData();
      ResourceDataManager.defaultInstance.injectResourceDatas( locale, data, true );
      rd.setContentData( data );
      
      rds.add( rd );
    }    
    
    // the client section is emtpy
    {
      ResponseData rd = new ResponseData();
      rd.setFlowData( UIIdentity.UM_EMPTY );
      rds.add( rd );
    }
    
    return rds;
  }
  
  public static List< ResponseData< ListUsersData > > getListUsersResponses( Locale locale, List< UserListData > userDatas )
  {
    List< ResponseData< ListUsersData > > listUsersResponses = new ArrayList< ResponseData< ListUsersData > >();
    
    ResponseData< ListUsersData >  listUsers = new ResponseData< ListUsersData >();
    listUsers.setFlowData( UIIdentity.UM_LIST_USERS );
    
    {
      ListUsersData data = new ListUsersData();
      ResourceDataManager.defaultInstance.injectResourceDatas( locale, data, true );
      data.setUserDatas( userDatas );
      listUsers.setContentData( data );
    }
    
    return listUsersResponses;
  }
}
