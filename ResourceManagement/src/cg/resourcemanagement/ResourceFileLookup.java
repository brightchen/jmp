package cg.resourcemanagement;

import java.io.File;

public class ResourceFileLookup
{
  private IResourceFileLookupStrategy lookupStrategy;
  
  public ResourceFileLookup(){}
  
  public ResourceFileLookup( IResourceFileLookupStrategy lookupStrategy )
  {
    setLookupStrategy( lookupStrategy );
  }
  
  public File[] getResourceFiles()
  {
    return getLookupStrategy().getResourceFiles();
  }

  public IResourceFileLookupStrategy getLookupStrategy()
  {
    if( lookupStrategy == null )
    {
      synchronized(this)
      {
        if( lookupStrategy == null )
        {
          lookupStrategy = createDefaultLookupStrategy();
        }
      }
    }
    return lookupStrategy;
  }

  
  public void setLookupStrategy( IResourceFileLookupStrategy lookupStrategy )
  {
    this.lookupStrategy = lookupStrategy;
  }
  
  protected IResourceFileLookupStrategy createDefaultLookupStrategy()
  {
    return new ResourceFileLookupByNameFormat();
  }
}
