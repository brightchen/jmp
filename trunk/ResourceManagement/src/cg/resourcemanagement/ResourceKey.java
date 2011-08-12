package cg.resourcemanagement;

public class ResourceKey
{
  private final String SEPERATOR = ".";
  
  private String moduleName;
  private String className;
  private String propertyName;
  
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
}
