package cg.common.reflect;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class ReflectionsBuilder
{
  private String includeRegex;
  private String excludeRegex;

  public Reflections buildSubTypeReflections()
  {
    //the urls must be set or use the empty urls and not package searched
    return new Reflections( new ConfigurationBuilder().filterInputsBy( getFilterBuilder() ).setUrls( ClasspathHelper.getUrlsForCurrentClasspath() ).setScanners( new SubTypesScanner() ) );
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
