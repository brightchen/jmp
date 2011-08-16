package cg.resourcemanagement;

import cg.common.util.StringUtil;
import cg.resourcemanagement.annotation.IResourceKey;

public class ResourceKey
{
  private final String SEPERATOR = ".";
  
  private String moduleName;
  private String className;
  private String propertyName;

  public ResourceKey(){}
  
  public ResourceKey( String moduleName, String className, String propertyName )
  {
    setModuleName( moduleName );
    setClassName( className );
    setPropertyName( propertyName );
  }

  public ResourceKey( IResourceKey resourceKey )
  {
    this( resourceKey == null ? null : resourceKey.moduleName(), 
          resourceKey == null ? null : resourceKey.className(), 
          null );
  }
  
  public String getModuleName()
  {
    return moduleName;
  }
  public void setModuleName( String moduleName )
  {
    this.moduleName = moduleName;
  }
  public String getClassName()
  {
    return className;
  }
  public void setClassName( String className )
  {
    this.className = className;
  }
  public String getPropertyName()
  {
    return propertyName;
  }
  public void setPropertyName( String propertyName )
  {
    this.propertyName = propertyName;
  }
  
  public String getKey()
  {
    return moduleName + SEPERATOR + className + SEPERATOR + propertyName;
  }
  
  public boolean isValid()
  {
    return !StringUtil.isNullOrEmpty( moduleName ) && !StringUtil.isNullOrEmpty( className ) 
        && !StringUtil.isNullOrEmpty( propertyName );
  }
}
