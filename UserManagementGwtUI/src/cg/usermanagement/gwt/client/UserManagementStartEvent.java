package cg.usermanagement.gwt.client;

import cg.usermanagement.gwt.shared.rpc.UserManagementTypicalCallback;

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
    getUserManagement().getStartUI( getLocale(), UserManagementTypicalCallback.instance );
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
}
