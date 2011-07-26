package cg.gwt.components.server.resource;

import java.util.List;
import java.util.Locale;

import cg.common.util.ReflectionUtil;
import cg.gwt.components.shared.data.ResourceData;
import cg.gwt.components.shared.data.UICompositeContentData;
import cg.gwt.components.shared.data.UIContentData;

public class ResourceDataManager
{
  private IResourceDataLookupStrategy lookupStrategy = new ResourceDataChainLookupStrategy();
  
  public static final ResourceDataManager defaultInstance = new ResourceDataManager();

  public ResourceDataManager(){ }
  
  public ResourceDataManager( IResourceDataLookupStrategy lookupStrategy )
  {
    setLookupStrategy( lookupStrategy );
  }
  
  public IResourceDataLookupStrategy getLookupStrategy()
  {
    return lookupStrategy;
  }

  public void setLookupStrategy( IResourceDataLookupStrategy lookupStrategy )
  {
    this.lookupStrategy = lookupStrategy;
  }

  public < RD extends ResourceData > RD getResourceData( Locale locale, Class< RD > resourceDataClass )
  {
    return lookupStrategy.getResourceData( locale, resourceDataClass );
  }
  
  /*
   * fill the resource data of the content data
   * create - should we create the ResourceData if the resourceData of the contentData is null
   * this methods go through the contentData and find the resource data which need to fill and then delegate to buildResourceData
   */
  public void fillResourceDatas( Locale locale, UIContentData contentData, boolean create )
  {
    ResourceData resourceData = contentData.getResourceData();
    if( resourceData != null || create )
    {
      //need to fill this resource data
      Class< ? extends ResourceData > resourceDataClass = getResourceDataType( contentData );
      if( resourceDataClass != null )
      {
        resourceData = getResourceData( locale, resourceDataClass );
        contentData.setResourceData( resourceData );
      }
    }
    
    if( !( contentData instanceof UICompositeContentData ) )
      return;
    
    UICompositeContentData compositeData = ( UICompositeContentData )contentData;
    List< UIContentData > subContentDatas = compositeData.getSubContentDatas();
    if( subContentDatas == null )
      return;
    
    for( UIContentData subContentData : subContentDatas )
    {
      fillResourceDatas( locale, subContentData, create );
    }
  }
  
  /*
   * the actually type of resourceData is the annotation type of UIContentData
   * should not get the return type of getResourceData() as it can always be ResourceData
   */
  public static Class< ? extends ResourceData > getResourceDataType( UIContentData contentData )
  {
    if( contentData.getResourceData() != null )
      return contentData.getResourceData().getClass();
    
    return ReflectionUtil.getGenericActualTypeArgumentClass( contentData.getClass(), UIContentData.class, ResourceData.class );
  }
  
  public static ResourceData createEmptyResourceData( UIContentData contentData )
  {
    try
    {
      Class<? extends ResourceData> resourceDataType = getResourceDataType( contentData );
      return resourceDataType.newInstance();
    }
    catch( Exception e )
    {
      e.printStackTrace();
      return null;
    }
  }
}