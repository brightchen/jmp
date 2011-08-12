package cg.gwt.components.server.resource;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import cg.gwt.components.annotation.IContentDataIndicator;

/*
 * get the list of sub content data by annotation which annotated in the getter/setter
 * the valid getters/setters are annotated by IContentDataIndicator and its isContentData is true
 */
public class SubContentDataAnnotationLookupStrategy extends AbstractSubContentDataLookup
{
  private static SubContentDataAnnotationLookupStrategy defaultInstance;
  
  public static SubContentDataAnnotationLookupStrategy defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( SubContentDataAnnotationLookupStrategy.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new SubContentDataAnnotationLookupStrategy();
        }
      }
    }
    
    return defaultInstance;
  }

  /*
   * for DataAnnotationLookupStrategy, only the valid getters/setters are annotated by IContentDataIndicator and its isContentData is true
   * @see cg.gwt.components.server.resource.AbstractSubContentDataLookup#filterGetterSetters(java.util.Set, java.util.Set)
   */
  @Override
  public void filterGetterSetters( Set< Method > getters, Set< Method > setters )
  {
    Set< Method > allMethods = new HashSet< Method >();
    allMethods.addAll( getters );
    allMethods.addAll( setters );
    
    if( allMethods.isEmpty() )
      return;
    
    Set< Method > contentDataMethods = new HashSet< Method >();
    for( Method method : allMethods )
    {
      IContentDataIndicator indicator = method.getAnnotation( IContentDataIndicator.class );
      if( indicator == null )
        continue;
      
      if( !indicator.isContentData() )
        continue;
      
      contentDataMethods.add( method );
    }
    
    getters.retainAll( contentDataMethods );
    setters.retainAll( contentDataMethods );

  }

}
