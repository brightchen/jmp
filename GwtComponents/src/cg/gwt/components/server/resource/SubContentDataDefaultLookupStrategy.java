package cg.gwt.components.server.resource;

import java.lang.reflect.Method;
import java.util.Set;

/*
 * If there are any getter/setter which annotated with IContentDataIndicator, delegate to the SubContentDataAnnotationLookupStrategy
 * else delegate to the SubContentDataTypeLookupStategy.
 * namely, it is not allowed to use both SubContentDataAnnotationLookupStrategy and SubContentDataTypeLookupStategy for one class
 */
public class SubContentDataDefaultLookupStrategy extends AbstractSubContentDataLookup
{
  private static SubContentDataDefaultLookupStrategy defaultInstance;
  
  public static SubContentDataDefaultLookupStrategy defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( SubContentDataDefaultLookupStrategy.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new SubContentDataDefaultLookupStrategy();
        }
      }
    }
    
    return defaultInstance;
  }

  /*
   * all getters/setters for UIContentData are valid
   * @see cg.gwt.components.server.resource.AbstractSubContentDataLookup#filterGetterSetters(java.util.Set, java.util.Set)
   */
  public void filterGetterSetters( Set< Method > getters, Set< Method > setters )
  {
    
  }

}
