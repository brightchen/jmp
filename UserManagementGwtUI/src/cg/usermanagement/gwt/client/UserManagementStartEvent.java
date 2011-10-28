package cg.usermanagement.gwt.client;

import java.util.List;

import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
import cg.gwt.components.shared.data.FrameData;
import cg.gwt.components.shared.data.ResponseData;

/*
 * data - the locale
 */
public class UserManagementStartEvent extends UserManagementEvent< String >
{
  public enum LOCALE
  {
    en_US,
    zh_CN
  }
  
  private String localeName = LOCALE.en_US.name();
//  private IUserManagementAsync userManagement = GWT.create( IUserManagement.class );
  
  @Override
  public void fire()
  {
    getUserManagement().getStartUI( getLocale(), new PopupFailureReasonCallback< FrameData >()
                                {
                                  @Override
                                  public void onSuccess( FrameData frameData )
                                  {
                                    onGetStartUISuccess( frameData );
                                  }
                                } );
  }
  
  public void setLocale( String localeName )
  {
    this.localeName = localeName;
  }
  
  public void setLocale( LOCALE locale )
  {
    setLocale( locale.name() );
  }
  
  public String getLocale()
  {
    return localeName;
  }
  
  @Override
  public String getData()
  {
    return getLocale();
  }

  protected void onGetStartUISuccess( FrameData frameData )
  {
    UserManagementUIFlow.onGetStartUISuccess( frameData );
  }
}
