package cg.gwt.components.server.resource;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import cg.common.util.ReflectionUtil;
import cg.common.util.StringUtil;
import cg.gwt.components.annotation.IContentDataAttributes;
import cg.gwt.components.shared.data.ResourceData;
import cg.gwt.components.shared.data.UICompositeContentData;
import cg.gwt.components.shared.data.UIContentData;
import cg.resourcemanagement.annotation.IResourceKey;

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

  public < RD extends ResourceData > RD getResourceData( Locale locale, Class< RD > resourceDataClass, ResourceDataContext context )
  {
    return lookupStrategy.getResourceData( locale, resourceDataClass, context );
  }
  
  /*
   * fill the resource data of the content data
   * create - should we create the ResourceData if the resourceData of the contentData is null
   * this methods go through the contentData and find the resource data which need to fill and then delegate to buildResourceData
   */
  public void injectResourceDatas( Locale locale, UIContentData contentData, boolean create )
  {
    ResourceDataContext context = new ResourceDataContext();
    getResourceDataContext( contentData, context );
    injectResourceDatas( locale, context, create );
  }
  
  @SuppressWarnings( "unchecked" )
  public void injectResourceDatas( Locale locale, ResourceDataContext context, boolean create )
  {
    UIContentData contentData = context.getOwnerContentData();
    ResourceData resourceData = contentData.getResourceData();

    if( resourceData != null || create )
    {
      //need to fill this resource data
      Class< ? extends ResourceData > resourceDataClass = getResourceDataClass( contentData );
      if( resourceDataClass != null )
      {
        // the context maybe changed during processing, so clone the context;
        resourceData = getResourceData( locale, resourceDataClass, context.clone() );
        contentData.setResourceData( resourceData );
      }
    }
    
//    if( !( contentData instanceof ICompositeContentData ) )
//      return;
    
//    ICompositeContentData compositeData = ( ICompositeContentData )contentData;
//    List< ? extends UIContentData > subContentDatas = compositeData.getSubContentDatas();
    List< ResourceDataContext > subResourceDataContexts = getSubResourceDataContexts( context );
    if( subResourceDataContexts == null )
      return;
    
    for( ResourceDataContext subContext : subResourceDataContexts )
    {
      injectResourceDatas( locale, subContext, create );
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

  /*
   * get the contexts( ownerContentData, superResourceKey ) of sub-content-resource data 
   */
  @SuppressWarnings( "unchecked" )
  public List< ResourceDataContext > getSubResourceDataContexts( ResourceDataContext context )
  {
    UIContentData contentData = context.getOwnerContentData();
    if( contentData == null )
      return null;
    
    //for composite content data, use the getSubContentDatas
    if( contentData instanceof UICompositeContentData )
    {
      UICompositeContentData compositeData = (UICompositeContentData)contentData;
      List< ? extends UIContentData > subContentDatas = compositeData.getSubContentDatas();
      if( subContentDatas == null || subContentDatas.size() == 0 )
        return Collections.emptyList();
      List< ResourceDataContext > resourDataContexts = new ArrayList< ResourceDataContext >();
      for( UIContentData subContentData : subContentDatas )
      {
        resourDataContexts.add( new ResourceDataContext( subContentData, context.getResourceKey() ) );
      }
      return resourDataContexts;
    }
    
    //get the ISubContentDataLookupStrategy
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
    return lookupStrategy.getSubResourceDataContexts( context );
  }
  
  /*
   * get the resource data context from the content data, 
   */
  public void getResourceDataContext( UIContentData contentData, ResourceDataContext context )
  {
    context.setOwnerContentData( contentData );
    Class<?> contentDataClass = contentData.getClass();
    
    //check whether the content-data-class is annotated by @IResourceKey
    {
      IResourceKey resourceKey = contentDataClass.getAnnotation( IResourceKey.class );
      applyResourceKey( resourceKey, context );
    }

    //check whether resource-data setter/getter is annotated by @IResourceKey
    {
      Method getResourceDataMethod = ReflectionUtil.getMethod( contentDataClass, "getResourceData", ReflectionUtil.NO_PARAMETER );
      IResourceKey resourceKey = getResourceDataMethod.getAnnotation( IResourceKey.class );
      applyResourceKey( resourceKey, context );
    }
  }
  
  public void applyResourceKey( IResourceKey resourceKey, ResourceDataContext context )
  {
    if( resourceKey == null )
      return;
    {
      String moduleName = resourceKey.moduleName();
      if( !StringUtil.isNullOrEmpty( moduleName ) )
        context.getNonNullResourceKey().setModuleName( moduleName );
    }
    {
      String className = resourceKey.className();
      if( !StringUtil.isNullOrEmpty( className ) )
        context.getNonNullResourceKey().setClassName( className );
    }

  }
}
