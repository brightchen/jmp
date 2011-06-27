package cg.resourcemanagement;

import java.util.Set;

public class ResourceFileLookup
{
  private IResourceFileLookupStrategy lookupStrategy;
  
  public ResourceFileLookup(){}
  
  public ResourceFileLookup( IResourceFileLookupStrategy lookupStrategy )
  {
    setLookupStrategy( lookupStrategy );
  }
  
  public Set< String > getResourceFiles()
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
