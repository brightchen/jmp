package cg.gwt.components.server.resource;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cg.common.util.ReflectionUtil;
import cg.gwt.components.shared.data.UIContentData;
import cg.resourcemanagement.ResourceKey;
import cg.resourcemanagement.annotation.IResourceKey;

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
      
      //get the resource key for this getter
      
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
  public static List< ResourceDataContext > getSubResourceDataContexts( ResourceDataContext context, 
                                                                        Map< Method, ResourceKey > getterMap, 
                                                                        Map< Method, ResourceKey > setterMap )
  {
    //for getters, should get its corresponding setter method in order to set sub-content-datas
    Map< Method, Method > map = ReflectionUtil.getCorrespondingSetters( getterMap.keySet() );
    for( Map.Entry< Method, Method > entry : map.entrySet() )
    {
      Method setter = entry.getValue();
      if( setter == null )
        continue;
      if( !ReflectionUtil.isParameterTypeCompatible( setter.getParameterTypes()[0], UIContentData.class ) )
        continue;

      setterMap.put( setter, mergeResourceKey( getResourceKeyFromMethod( setter ), getResourceKeyFromMethod( entry.getKey() ) ) );
    }

    List< ResourceDataContext > subResourceContexts = new ArrayList< ResourceDataContext >();
    UIContentData contentData = context.getOwnerContentData();
    for( Map.Entry< Method, ResourceKey > entry : setterMap.entrySet() )
    {
      try
      {
        Method setter = entry.getKey();
        
        Class< ? extends UIContentData > subContentDataClass = (Class< ? extends UIContentData >)setter.getParameterTypes()[0];
        UIContentData subContentData = subContentDataClass.newInstance();
      //should call the setter method to set the sub-content-data into this contentData
        setter.invoke( contentData, subContentData );
        
        //add the ( sub-content-data, resource key ) into list
        subResourceContexts.add( new ResourceDataContext( subContentData, entry.getValue() ) );
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
    }
    return subResourceContexts;
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
  
  public static Map< Method, ResourceKey > getMethodResourceKeyMap( Set< Method > methods )
  {
    Map< Method, ResourceKey > map = new HashMap< Method, ResourceKey >();
    for( Method method : methods )
    {
      map.put( method, getResourceKeyFromMethod( method ) );
    }
    return map;
  }
  
  public static ResourceKey getResourceKeyFromMethod( Method method )
  {
    IResourceKey resourceKey = method.getAnnotation( IResourceKey.class );
    return new ResourceKey( resourceKey );
  }
  
  /*
   * merge the resource key annotated in the getter/setter for same property
   * only one method should be annotated
   */
  public static ResourceKey mergeResourceKey( ResourceKey resourceKey1, ResourceKey resourceKey2 )
  {
    return ( resourceKey1 == null ) ? resourceKey2 : resourceKey1;
  }
}
