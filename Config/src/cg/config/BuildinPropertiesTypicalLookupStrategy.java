package cg.config;

import java.util.Properties;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class BuildinPropertiesTypicalLookupStrategy implements IPropertiesStrategy
{
  private String includeRegex;
  private String excludeRegex;
  private Reflections buildinPropertiesReflections;
  
  public BuildinPropertiesTypicalLookupStrategy(){}
  
  public BuildinPropertiesTypicalLookupStrategy( String includeRegex, String excludeRegex )
  {
    setIncludeRegex( includeRegex );
    setExcludeRegex( excludeRegex );
  }
  
  @Override
  public Properties getProperties()
  {
    Set< Class< ? extends IBuildinProperties > > propsClasses = getBuildinPropertiesReflections().getSubTypesOf( IBuildinProperties.class );
    if( propsClasses == null || propsClasses.size() == 0 )
    {
      return new Properties();
    }

    MergeableProperties props = new MergeableProperties();
    
    for( Class< ? extends IBuildinProperties > propsClass : propsClasses )
    {
      props.merge( getProperties( propsClass ) );
    }
    return props;
  }

  protected Properties getProperties( Class< ? extends IBuildinProperties > propertiesClass )
  {
    try
    {
      IBuildinProperties propertiesInstance = propertiesClass.newInstance();
      return propertiesInstance.getProperties();
    }
    catch( Exception e )
    {
      return null;
    }
  }
  
  
  private Reflections getBuildinPropertiesReflections()
  {
    if( buildinPropertiesReflections == null )
    {
      synchronized( BuildinPropertiesTypicalLookupStrategy.class )
      {
        if( buildinPropertiesReflections == null )
        {
          //the urls must be set or use the empty urls and not package searched
          buildinPropertiesReflections = new Reflections( new ConfigurationBuilder().filterInputsBy( getFilterBuilder() ).setUrls( ClasspathHelper.getUrlsForCurrentClasspath() ).setScanners( new SubTypesScanner() ) );
        }
      }
    }
    return buildinPropertiesReflections;
  }

  protected FilterBuilder getFilterBuilder()
  {
    FilterBuilder fb = new FilterBuilder();
    fb.include( getIncludeRegex() );
    String er = getExcludeRegex();
    if( er != null && !er.isEmpty() )
      fb.exclude( er );
    return fb;
  }

  protected String getDefaultIncludeRegex()
  {
    return "cg.*";   //not "cg*" or "cg."
  }
  public String getIncludeRegex()
  {
    return ( includeRegex == null ) ? getDefaultIncludeRegex() : includeRegex;
  }

  public void setIncludeRegex( String includeRegex )
  {
    this.includeRegex = includeRegex;
  }

  public String getExcludeRegex()
  {
    return excludeRegex;
  }

  public void setExcludeRegex( String excludeRegex )
  {
    this.excludeRegex = excludeRegex;
  }

  
}
