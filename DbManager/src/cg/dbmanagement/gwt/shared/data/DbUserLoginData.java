package cg.dbmanagement.gwt.shared.data;

import cg.gwt.components.shared.data.PartData;

public class DbUserLoginData implements PartData
{
  private static final long serialVersionUID = 2427711317296226011L;
  
  private String[] supportedDatabases;
  private String userName;
  private String password;
  
  public String[] getSupportedDatabases()
  {
    return supportedDatabases;
  }
  public void setSupportedDatabases( String[] supportedDatabases )
  {
    this.supportedDatabases = supportedDatabases;
  }
  public String getUserName()
  {
    return userName;
  }
  public void setUserName( String userName )
  {
    this.userName = userName;
  }
  public String getPassword()
  {
    return password;
  }
  public void setPassword( String password )
  {
    this.password = password;
  }
  
  

}
