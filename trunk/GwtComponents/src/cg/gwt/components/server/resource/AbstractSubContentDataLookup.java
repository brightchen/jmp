package cg.gwt.components.server.resource;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import cg.gwt.components.shared.data.UIContentData;

@SuppressWarnings( "rawtypes" ) 
public abstract class AbstractSubContentDataLookup implements ISubContentDataLookupStrategy
{

  @Override
  public List< UIContentData > getSubContentData( UIContentData contentData )
  {
    Class< ? extends UIContentData > contentDataClass = contentData.getClass();
    Set< Method > getters = ContentDataUtil.getSubContentDataGetters( contentDataClass );
    //setter only take one parameter
    Set< Method > setters = ContentDataUtil.getSubContentDataSetters( contentDataClass );

    filterGetterSetters( getters, setters );
    
    return ContentDataUtil.getSubContentDatas( contentData, getters, setters );
  }

  public abstract void filterGetterSetters( Set< Method > getters, Set< Method > setters );

}
