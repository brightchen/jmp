package cg.config;

import java.util.Properties;
import java.util.Set;

import org.reflections.Reflections;

import cg.common.reflect.ReflectionsBuilder;

public class BuildinPropertiesTypicalLookupStrategy implements IPropertiesStrategy
{
  private ReflectionsBuilder reflectionsBuilder;
  private Reflections buildinPropertiesReflections;
  
  public BuildinPropertiesTypicalLookupStrategy()
  {
    reflectionsBuilder = new ReflectionsBuilder();
  }
  
  public BuildinPropertiesTypicalLookupStrategy( String includeRegex, String excludeRegex )
  {
    this();
    reflectionsBuilder.setIncludeRegex( includeRegex );
    reflectionsBuilder.setExcludeRegex( excludeRegex );
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
      synchronized( this )
      {
        if( buildinPropertiesReflections == null )
        {
          //the urls must be set or use the empty urls and not package searched
          buildinPropertiesReflections = reflectionsBuilder.buildSubTypeReflections();
        }
      }
    }
    return buildinPropertiesReflections;
  }

}
