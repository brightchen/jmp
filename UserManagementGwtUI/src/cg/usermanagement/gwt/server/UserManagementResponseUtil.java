package cg.usermanagement.gwt.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cg.contentdata.management.ResourceDataManager;
import cg.gwt.components.shared.data.Frame;
import cg.gwt.components.shared.data.FrameData;
import cg.gwt.components.shared.data.ResponseData;
import cg.gwt.components.shared.data.UIIdentity;
import cg.services.session.SessionManager;
import cg.usermanagement.gwt.shared.data.AccountLoginData;
import cg.usermanagement.gwt.shared.data.ListUsersGridData;
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
  public static FrameData getUserManagementPanelDatas( Locale locale )
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
    
    return new FrameData( Frame.UMF_CONTROL_PANEL, rds );
  }
  
  public static FrameData getListUsersResponses( Locale locale, List< UserListData > userDatas )
  {
    List< ResponseData< ListUsersGridData > > listUsersResponses = new ArrayList< ResponseData< ListUsersGridData > >();
    
    ResponseData< ListUsersGridData >  listUsers = new ResponseData< ListUsersGridData >();
    listUsers.setFlowData( UIIdentity.UM_LIST_USERS );
    
    {
      ListUsersGridData data = new ListUsersGridData();
      ResourceDataManager.defaultInstance.injectResourceDatas( locale, data, true );
      data.setRecordList( userDatas );
      listUsers.setContentData( data );
    }
    
    listUsersResponses.add( listUsers );
    
    FrameData frameData = new FrameData( Frame.UMF_LIST_USERS );
    for( ResponseData< ListUsersGridData > rd : listUsersResponses )
    {
      frameData.addResponseData( rd );
    }
    return frameData;
  }
}
