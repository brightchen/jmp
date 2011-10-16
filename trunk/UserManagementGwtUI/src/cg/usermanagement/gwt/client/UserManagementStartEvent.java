package cg.usermanagement.gwt.client;

import java.util.List;

import cg.gwt.components.shared.callback.PopupFailureReasonCallback;
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
    getUserManagement().getStartUI( getLocale(), new PopupFailureReasonCallback< List< ResponseData<?> > >()
                                {
                                  @Override
                                  public void onSuccess( List< ResponseData<?> > responseDatas )
                                  {
                                    onGetStartUISuccess( responseDatas );
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

  protected void onGetStartUISuccess( List< ResponseData<?> > responseDatas )
  {
    UserManagementUIFlow.onGetStartUISuccess( responseDatas );
  }
}
