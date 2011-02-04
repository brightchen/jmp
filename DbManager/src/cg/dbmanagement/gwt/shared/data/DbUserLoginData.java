package cg.dbmanagement.gwt.shared.data;

import cg.gwt.components.shared.data.PartData;

public class DbUserLoginData implements PartData
{
  private static final long serialVersionUID = 2427711317296226011L;
  
  private String[] supportedDatabases;
  private int selectedDatabaseIndex;
  private String jdbcUrl;
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
  
  public String getJdbcUrl()
  {
    return jdbcUrl;
  }
  public void setJdbcUrl( String jdbcUrl )
  {
    this.jdbcUrl = jdbcUrl;
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
  public int getSelectedDatabaseIndex()
  {
    return selectedDatabaseIndex;
  }
  public void setSelectedDatabaseIndex( int selectedDatabaseIndex )
  {
    this.selectedDatabaseIndex = selectedDatabaseIndex;
  }
  public String getSelectedDatabase()
  {
    if( supportedDatabases == null || supportedDatabases.length == 0 )
      return null;
    return supportedDatabases[ getSelectedDatabaseIndex() ];
  }
  

}
