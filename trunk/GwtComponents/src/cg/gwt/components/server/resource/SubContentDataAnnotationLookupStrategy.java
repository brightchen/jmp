package cg.gwt.components.server.resource;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
                                                       new Class<?>[]{ UIContentData.class }, Modifier.PUBLIC );
    
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

    //for getters, should get its corresponding setter method in order to set sub-content-datas
    Map< Method, Method > map = ReflectionUtil.getCorrespondingSetters( getters );
    for( Map.Entry< Method, Method > entry : map.entrySet() )
    {
      Method setter = entry.getValue();
      if( setter == null )
        continue;
      if( !ReflectionUtil.isParameterTypeCompatible( setter.getParameterTypes()[0], UIContentData.class ) )
        continue;

      setters.add( setter );
    }

    List< UIContentData > subContentDatas = new ArrayList< UIContentData >();
    for( Method setter : setters )
    {
      try
      {
        Class< ? extends UIContentData > subContentDataClass = (Class< ? extends UIContentData >)setter.getParameterTypes()[0];
        UIContentData subContentData = subContentDataClass.newInstance();
        setter.invoke( contentData, subContentData );   //should call the setter method to set the sub-content-data into this contentData
        subContentDatas.add( subContentData );
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
    }
    return null;
  }

}
