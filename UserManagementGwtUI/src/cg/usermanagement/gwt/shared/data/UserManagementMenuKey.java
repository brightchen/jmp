package cg.usermanagement.gwt.shared.data;


public enum UserManagementMenuKey
{
  control$locale
  ;
  
  public String getStringKey()
  {
    return name().replace( '$', '.' );
  }

}
