package cg.usermanagement.gwt.client;

import cg.contentdata.shared.UIContentData;
import cg.gwt.components.client.ui.EmptyUI;
import cg.gwt.components.client.ui.PanelCompositeUI;
import cg.gwt.components.client.ui.grid.GridUI;
import cg.gwt.components.shared.data.FrameData;
import cg.gwt.components.shared.data.ResponseData;
import cg.gwt.components.shared.data.UIFlowData;
import cg.gwt.components.shared.data.UIIdentity;
import cg.gwtui.client.GwtUiManagementControlSectionUI;
import cg.gwtui.client.GwtUiManagementMenuEvent;
import cg.gwtui.client.GwtUiManagementStartEvent;
import cg.gwtui.client.GwtUiManagementUIFlow;
import cg.gwtui.client.SectionUI;
import cg.gwtui.shared.data.ControlSectionData;
import cg.gwtui.shared.rpc.GwtUiManagementTypicalCallback;
import cg.usermanagement.gwt.client.role.RoleDetailUI;
import cg.usermanagement.gwt.client.user.SearchUserUI;
import cg.usermanagement.gwt.shared.data.ListUsersGridData;
import cg.usermanagement.gwt.shared.data.RoleDetailData;
import cg.usermanagement.gwt.shared.data.SearchUserData;
import cg.usermanagement.gwt.shared.data.UserManagementPanelData;
import cg.usermanagement.gwt.shared.data.UserManagementStartData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/*
 * the web server side will control the UI flow instead the client side.
 * for the first page, the UIFlow from the client will send a ajax call to the server side to get the startPage
 * for every request send to the web server, the response will contain the information of next UI ( include the required data )
 * 
 * interface between web client and server:
 * 1. the web server control the page flow, the data fetched from the server decide which page/UIs should be displayed
 * 2. the user management page can be divided into 3 section: 
 *   control section: this is the common section could be used for all modules
 *   user management panel: this is the common section for user management module
 *   client section: this is the section displaying current operation/component.
 *   
 * In most case, only the client section need to be refreshed. But the user management panel or control section
 * should also be refresh-able. the system should define the interface which supports both.
 * 
 * the response data list only contains the sections which need to be refreshed.
 */
@SuppressWarnings( "rawtypes" ) 
public class UserManagementUIFlow extends GwtUiManagementUIFlow
{
  //the static attribute is safe as this is client code and run in the web browser.
  //the static is only static for one client web browser.
  // in order to be inherited by other module, don't use static method here.
  
  //  private static UserManagementControlSectionUI controlSectionUI;
  //  private static ClientSectionUI clientSectionUI = new ClientSectionUI();
  
  //it should only have one popup at any time
//  private static PopupDecorator< ?, Widget > currentPopup;    
//  private static PopupDecorator<?,?> roleDetailPopup;
  
  private static UserManagementUIFlow instance;
  
  private SectionUI userManagementPanelSectionUI = null;

  public static UserManagementUIFlow getInstance()
  {
    if( instance == null )
    {
      synchronized( UserManagementUIFlow.class )
      {
        if( instance == null )
          instance = new UserManagementUIFlow();
      }
    }
    return instance;
  }
  

  @Override
  protected GwtUiManagementStartEvent createStartEvent()
  {
    GwtUiManagementStartEvent startEvent = new GwtUiManagementStartEvent()
    {
      @Override
      public void fire()
      {
        GwtUiManagementTypicalCallback callback = new GwtUiManagementTypicalCallback();
        callback.setUIFlow( UserManagementUIFlow.this );      //let the SpUIFlow handle the UI response
        getUiManagement().getStartUI( getLocale(),  callback );
      }
    };
    startEvent.setUiManagement( (IUserManagementAsync)GWT.create( IUserManagement.class ) );
    
    return startEvent;
  }
  
  
  @Override
  protected Widget buildControlSection( ControlSectionData controlSectionContentData )
  {
    return new GwtUiManagementControlSectionUI( controlSectionContentData )
                {
                  @Override
                  public GwtUiManagementMenuEvent getMenuEvent()
                  {
                    return new GwtUiManagementMenuEvent( UserManagementUIFlow.this );
                  }
                };
  }
  
  
  @SuppressWarnings( "unchecked")
  @Override
  protected Widget buildExtraUI( ResponseData<?> responseData )
  {
    UIFlowData flowData = responseData.getFlowData();
    UIIdentity identity = flowData.getUiIdentity();
    UIContentData contentData = responseData.getContentData();
    if( UIIdentity.UM_START.equals( identity ) )
    {
      return new UserManagementStartUI( (UserManagementStartData)contentData );   
    }
    if( UIIdentity.UM_CONTROL_PANEL.equals( identity ) )
    {
      return new UserManagementPanelUI( (UserManagementPanelData)contentData ); 
    }
    if( UIIdentity.UM_EMPTY.equals( identity ) )
    {
      return EmptyUI.instance();
    }
    if( UIIdentity.UM_SEARCH_USER.equals( identity ) )
    {
      return new SearchUserUI( (SearchUserData)contentData );
    }
    if( UIIdentity.UM_SEARCH_ACCOUNT.equals( identity ) )
    {
    }
    if( UIIdentity.UM_ADD_ACCOUNT.equals( identity ) )
    {
    }
    if( UIIdentity.UM_SEARCH_ROLE.equals( identity ) )
    {
    }
    if( UIIdentity.UM_ADD_ROLE.equals( identity ) )
    {
      return new RoleDetailUI( (RoleDetailData)contentData );
    }
    if( UIIdentity.UM_ADD_PERMISSION.equals( identity ) )
    {
    }
    if( UIIdentity.UM_LIST_USERS.equals( identity ) )
    {
      return new GridUI( (ListUsersGridData)contentData );
    }

    throw new IllegalStateException( "Invalid UIIdentity: " +  responseData.getFlowData().getUiIdentity().name() );
  }
  
  ////////////////////////////////////

  
  @SuppressWarnings( "unchecked")
  public void buildPage()
  {
    if( pageUI != null )
      return;
    controlSectionUI = new SectionUI();
    clientSectionUI = new SectionUI();
    userManagementPanelSectionUI = new SectionUI();

    //have both control section and client section
    pageUI = new PanelCompositeUI();
    pageUI.setContainer( new VerticalPanel() );
    pageUI.addChild( controlSectionUI );
    pageUI.addChild( userManagementPanelSectionUI );
    pageUI.addChild( clientSectionUI );
    
    // the UI's onLoad will be called when adding this component to RootPanel(), which calls build() method.
    // so, all the sub-componenets must be added to the pageUI before it added to the RootPanel();
    RootPanel.get().clear();
    RootPanel.get().add( pageUI );

  }
  
  
  /**
   * handle the none client section response data
   * @param frameData
   * @param responseData
   * @return true if the response data is none client section data
   */
  @SuppressWarnings( "unchecked")
  public boolean handleNoneClientSectionResponseData( FrameData frameData, ResponseData responseData )
  {
    UIFlowData flowData = responseData.getFlowData();
    UIIdentity identity = flowData.getUiIdentity();
    if( UIIdentity.CONTROL_SECTION.equals( identity ) )
    {
      //generic control section, maybe used by other module(s)
      controlSectionUI.setComponent( buildUI( responseData ) );
      controlSectionUI.refresh();
      return true;
    }
    
    if( UIIdentity.UM_CONTROL_PANEL.equals( identity ) )
    {
      //user management control panel, for user management module only
      userManagementPanelSectionUI.setComponent( buildUI( responseData ) );
      userManagementPanelSectionUI.refresh();
      return true;
    }
    
    return false;
  }
  
}
