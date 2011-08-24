package cg.contentdata.management;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cg.contentdata.shared.UIContentData;
import cg.resourcemanagement.ResourceKey;

@SuppressWarnings( "rawtypes" ) 
public abstract class AbstractSubContentDataLookup implements ISubContentDataLookupStrategy
{

  @Override
  public List< ResourceDataContext > getSubResourceDataContexts( ResourceDataContext context )
  {
    UIContentData contentData = context.getOwnerContentData();
    Class< ? extends UIContentData > contentDataClass = contentData.getClass();
    Set< Method > getters = ContentDataUtil.getSubContentDataGetters( contentDataClass );
    //setter only take one parameter
    Set< Method > setters = ContentDataUtil.getSubContentDataSetters( contentDataClass );

    filterGetterSetters( getters, setters );
    
    Map< Method, ResourceKey > getterMap = ContentDataUtil.getMethodResourceKeyMap( getters );
    Map< Method, ResourceKey > setterMap = ContentDataUtil.getMethodResourceKeyMap( setters );

    return ContentDataUtil.getSubResourceDataContexts( context, getterMap, setterMap );
  }

  public abstract void filterGetterSetters( Set< Method > getters, Set< Method > setters );

}
