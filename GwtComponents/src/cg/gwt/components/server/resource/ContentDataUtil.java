package cg.gwt.components.server.resource;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cg.common.util.ReflectionUtil;
import cg.gwt.components.shared.data.UIContentData;

@SuppressWarnings( "rawtypes" ) 
public class ContentDataUtil
{
  /*
   * get the all get method of sub content datas
   */
  public static Set< Method > getSubContentDataGetters( Class< ? extends UIContentData > contentDataClass )
  {
    return ReflectionUtil.getMethods( contentDataClass, ReflectionUtil.GET_METHOD_PATTERN, ReflectionUtil.NO_PARAMETER, Modifier.PUBLIC );
  }

  /*
   * get the all set method of sub content datas
   */
  public static Set< Method > getSubContentDataSetters( Class< ? extends UIContentData > contentDataClass )
  {
    return ReflectionUtil.getMethods( contentDataClass, ReflectionUtil.SET_METHOD_PATTERN, new Class<?>[]{ UIContentData.class }, Modifier.PUBLIC );
  }
  
  /*
   * get the list of the sub-content-data via getters/setters
   * the input getters/setters should be valid
   */
  @SuppressWarnings( "unchecked" )
  public static List< UIContentData > getSubContentDatas( UIContentData contentData, Set< Method > getters, Set< Method > setters )
  {
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
    return subContentDatas;
  }
}
