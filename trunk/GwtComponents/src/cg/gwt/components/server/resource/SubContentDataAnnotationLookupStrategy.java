package cg.gwt.components.server.resource;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cg.common.util.ReflectionUtil;
import cg.gwt.components.annotation.IContentDataIndicator;
import cg.gwt.components.shared.data.UIContentData;

/*
 * get the list of sub content data by annotation which annotated in the getter/setter
 */
public class SubContentDataAnnotationLookupStrategy implements ISubContentDataLookupStrategy
{
  @Override
  public List< UIContentData > getSubContentData( UIContentData contentData )
  {
    Class< ? extends UIContentData > contentDataClass = contentData.getClass();
    Set< Method > getters = ReflectionUtil.getMethods( contentDataClass, ReflectionUtil.GET_METHOD_PATTERN, 
                                                       ReflectionUtil.NO_PARAMETER, Modifier.PUBLIC );
    //setter only take one parameter
    Set< Method > setters = ReflectionUtil.getMethods( contentDataClass, ReflectionUtil.SET_METHOD_PATTERN, 
                                                       new Class<?>[]{ Object.class }, Modifier.PUBLIC );
    
    Set< Method > allMethods = new HashSet< Method >();
    allMethods.addAll( getters );
    allMethods.addAll( setters );
    
    if( allMethods.isEmpty() )
      return Collections.emptyList();
    
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
    
    //++++
    // TODO Auto-generated method stub
    return null;
  }

}
