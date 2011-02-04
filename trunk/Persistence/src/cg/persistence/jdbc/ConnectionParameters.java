package cg.persistence.jdbc;

import cg.common.util.Comparator;

public class ConnectionParameters implements Comparable< ConnectionParameters >
{
  private String driverClass;
  private String url;
  private String userName;
  private String password;
  private String validationQuery;
  public String getDriverClass()
  {
    return driverClass;
  }
  public void setDriverClass( String driverClass )
  {
    this.driverClass = driverClass;
  }
  public String getUrl()
  {
    return url;
  }
  public void setUrl( String url )
  {
    this.url = url;
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
  public String getValidationQuery()
  {
    return validationQuery;
  }
  public void setValidationQuery( String validationQuery )
  {
    this.validationQuery = validationQuery;
  }
  
  public String[] getCompareKeys()
  {
    return new String[]{ driverClass, url, userName };
  }
  //compare by ( driverClass, url, userName )
  @Override
  public int compareTo( ConnectionParameters other )
  {
    return Comparator.CompareArray( getCompareKeys(), other.getCompareKeys() );
  }
  
  
}
