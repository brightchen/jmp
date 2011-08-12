package cg.gwt.components.server.resource;

import java.util.List;
import java.util.Locale;

import cg.gwt.components.annotation.IContentDataAttributes;
import cg.gwt.components.shared.data.ResourceData;
import cg.gwt.components.shared.data.UICompositeContentData;
import cg.gwt.components.shared.data.UIContentData;

@SuppressWarnings( "rawtypes" )
public class ResourceDataManager
{
  public static final ResourceDataManager defaultInstance = new ResourceDataManager();

  private IResourceDataLookupStrategy lookupStrategy = new ResourceDataChainLookupStrategy();
  private IResourceDataClassStrategy resourceDataClassStrategy = new ResourceDataClassChainStrategy();

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

  public < RD extends ResourceData > RD getResourceData( Locale locale, UIContentData contentData, Class< RD > resourceDataClass )
  {
    return lookupStrategy.getResourceData( locale, contentData, resourceDataClass );
  }
  
  /*
   * fill the resource data of the content data
   * create - should we create the ResourceData if the resourceData of the contentData is null
   * this methods go through the contentData and find the resource data which need to fill and then delegate to buildResourceData
   */
  @SuppressWarnings( "unchecked" )
  public void fillResourceDatas( Locale locale, UIContentData contentData, boolean create )
  {
    ResourceData resourceData = contentData.getResourceData();
    if( resourceData != null || create )
    {
      //need to fill this resource data
      Class< ? extends ResourceData > resourceDataClass = getResourceDataClass( contentData );
      if( resourceDataClass != null )
      {
        resourceData = getResourceData( locale, contentData, resourceDataClass );
        contentData.setResourceData( resourceData );
      }
    }
    
//    if( !( contentData instanceof ICompositeContentData ) )
//      return;
    
//    ICompositeContentData compositeData = ( ICompositeContentData )contentData;
//    List< ? extends UIContentData > subContentDatas = compositeData.getSubContentDatas();
    List< ? extends UIContentData > subContentDatas = getSubContentDatas( contentData );
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
  public Class< ? extends ResourceData > getResourceDataClass( UIContentData contentData )
  {
    return resourceDataClassStrategy.getResourceDataClass( contentData );
  }
  
  public ResourceData createEmptyResourceData( UIContentData contentData )
  {
    try
    {
      Class<? extends ResourceData> resourceDataType = getResourceDataClass( contentData );
      return resourceDataType.newInstance();
    }
    catch( Exception e )
    {
      e.printStackTrace();
      return null;
    }
  }
  
  // get the list of the sub-content-data of contentData
  @SuppressWarnings( "unchecked" )
  public List< ? extends UIContentData > getSubContentDatas( UIContentData contentData )
  {
    if( contentData == null )
      return null;
    
    //for composite content data, use the getSubContentDatas
    if( contentData instanceof UICompositeContentData )
    {
      UICompositeContentData compositeData = (UICompositeContentData)contentData;
      return  compositeData.getSubContentDatas();
    }
    
    Class< ? extends ISubContentDataLookupStrategy > lookupStrategyClass = null;
    Class< ? extends UIContentData > contentDataClass = contentData.getClass();
    IContentDataAttributes attributes = contentDataClass.getAnnotation( IContentDataAttributes.class );
    if( attributes != null )
    {
      if( !attributes.isComposite() )
        return null;
      lookupStrategyClass = attributes.subContentDataLookupStrategy();
    }
    
    ISubContentDataLookupStrategy lookupStrategy = null;
    if( lookupStrategyClass == null )
    {
      //don't have IContentDataAttributes annotation or doesn't specify the lookup strategy
      //use the default lookup strategy
      lookupStrategy = SubContentDataDefaultLookupStrategy.defaultInstance();
    }
    else
    {
      try
      {
        lookupStrategy = lookupStrategyClass.newInstance();
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
    }
    //get contentData's @IContentDataAttributes
    return lookupStrategy.getSubContentData( contentData );
  }
}
