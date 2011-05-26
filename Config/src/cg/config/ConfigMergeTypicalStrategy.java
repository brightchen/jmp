package cg.config;

import java.util.Properties;


/*
 * this strategy get the properties with following sequence
 * - command line properties
 * - deployment configured properties: specific for one deployment
 * - default configured properties: for each project, same to different deployment
 * - build-in properties: properties written in source code
 */
public class ConfigMergeTypicalStrategy implements IPropertiesStrategy 
{
  protected String defaultPropertiesDefaultFileName = "defaultProperties.properties";
  protected String defaultPropertiesFileNameProperty = "defaultPropertiesFileName";
  //how to set the deployment properties file path property, it should be the deployment bin directory
  protected String deploymentPropertiesFilePathProperty = "";
  
  protected Properties buildinProperties;
  protected IPropertiesFileLookupStrategy defaultPropertiesFileLookupStrategy;
  protected IPropertiesFileLookupStrategy deploymentPropertiesFileLookupStrategy;
  
  protected Properties properties;
  
  public ConfigMergeTypicalStrategy()
  {
  }

  @Override
  public Properties getProperties()
  {
    if( properties == null )
    {
      MergeableProperties props = new MergeableProperties();
      props.merge( getBuildinProperties() );
      props.merge( getDefaultProperties() );
      props.merge( getDeploymentProperties() );
      props.merge( getCommandLineProperties() );
      
      properties = props;
    }
    
    return properties;
  }

  public Properties getBuildinProperties()
  {
    return buildinProperties;
  }

  public void setBuildinProperties( Properties buildinProperties )
  {
    this.buildinProperties = buildinProperties;
  }
  
  public Properties getDefaultProperties()
  {
    ConfigedProperties props = new ConfigedProperties();
    props.setPropertiesFileLookupStrategy( getDefaultPropertiesFileLookupStrategy() );
    return props.getProperties();
  }

  public Properties getDeploymentProperties()
  {
    ConfigedProperties props = new ConfigedProperties();
    props.setPropertiesFileLookupStrategy( getDeploymentPropertiesFileLookupStrategy() );
    return props.getProperties();
  }

  public Properties getCommandLineProperties()
  {
    return System.getProperties();
  }

  protected IPropertiesFileLookupStrategy createDefaultPropertiesFileLookupStrategy()
  {
    if( defaultPropertiesFileLookupStrategy == null )
    {
      synchronized( this )
      {
        if( defaultPropertiesFileLookupStrategy == null )
        {
          PropertiesFileLookupTypicalStrategy strategy = new PropertiesFileLookupTypicalStrategy();
          strategy.setDefaultPropertiesFileName( defaultPropertiesDefaultFileName );
          strategy.setPropertiesFileNameProperty( defaultPropertiesFileNameProperty );
          defaultPropertiesFileLookupStrategy = strategy;
        }
      }
    }
    return defaultPropertiesFileLookupStrategy; 
  }

  protected IPropertiesFileLookupStrategy getDefaultPropertiesFileLookupStrategy()
  {
    if( defaultPropertiesFileLookupStrategy == null )
    {
      createDefaultPropertiesFileLookupStrategy();
    }
    return defaultPropertiesFileLookupStrategy;
  }

  public void setDefaultPropertiesFileLookupStrategy( IPropertiesFileLookupStrategy defaultPropertiesFileLookupStrategy )
  {
    this.defaultPropertiesFileLookupStrategy = defaultPropertiesFileLookupStrategy;
  }

  
  protected IPropertiesFileLookupStrategy createDeploymentPropertiesFileLookupStrategy()
  {
    if( deploymentPropertiesFileLookupStrategy == null )
    {
      synchronized( this )
      {
        if( deploymentPropertiesFileLookupStrategy == null )
        {
          // the deployment properties file maybe outside the classpath, so use the filePath instead of fileName
          PropertiesFileLookupTypicalStrategy strategy = new PropertiesFileLookupTypicalStrategy();
          strategy.setPropertiesFilePathProperty( deploymentPropertiesFilePathProperty );
          deploymentPropertiesFileLookupStrategy = strategy;
        }
      }
    }
    return deploymentPropertiesFileLookupStrategy; 
  }

  protected IPropertiesFileLookupStrategy getDeploymentPropertiesFileLookupStrategy()
  {
    if( deploymentPropertiesFileLookupStrategy == null )
    {
      createDeploymentPropertiesFileLookupStrategy();
    }
    return deploymentPropertiesFileLookupStrategy;
  }

  public void setDeploymentPropertiesFileLookupStrategy( IPropertiesFileLookupStrategy deploymentPropertiesFileLookupStrategy )
  {
    this.deploymentPropertiesFileLookupStrategy = deploymentPropertiesFileLookupStrategy;
  }

  public void setDefaultPropertiesDefaultFileName( String defaultPropertiesDefaultFileName )
  {
    this.defaultPropertiesDefaultFileName = defaultPropertiesDefaultFileName;
  }

  public void setDefaultPropertiesFileNameProperty( String defaultPropertiesFileNameProperty )
  {
    this.defaultPropertiesFileNameProperty = defaultPropertiesFileNameProperty;
  }

  public void setDeploymentPropertiesFilePathProperty( String deploymentPropertiesFilePathProperty )
  {
    this.deploymentPropertiesFilePathProperty = deploymentPropertiesFilePathProperty;
  }
}
