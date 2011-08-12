package cg.gwt.components.server.resource;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
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
    Set< Method > getters = ReflectionUtil.getMethods( contentDataClass, ReflectionUtil.GET_METHOD_PATTERN, 
                                                       ReflectionUtil.NO_PARAMETER, Modifier.PUBLIC );
    Set< Method > validGetters = new HashSet< Method >();
    for( Method getter : getters )
    {
      if( !isValidSubContentDataGetter( getter ) )
        continue;
      validGetters.add( getter );
    }
    return validGetters;
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
  
  public static boolean isValidSubContentDataSetter( Method method )
  {
    if( method == null || !ReflectionUtil.isTypicalSetterMethod( method ) )
      return false;
    
    // the parameter should be compatible to UIContentData
    Class< ? > parameterType = method.getParameterTypes()[0];
    
    return ReflectionUtil.isParameterTypeCompatible( parameterType, UIContentData.class );
  }
  
  public static boolean isValidSubContentDataGetter( Method method )
  {
    if( method == null || !ReflectionUtil.isTypicalGetterMethod( method ) )
      return false;
    
    // the return type
    Class< ? > returnType = method.getReturnType();
    
    return ReflectionUtil.isParameterTypeCompatible( returnType, UIContentData.class );
  }
}
