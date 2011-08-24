package cg.contentdata.management;

import java.util.ArrayList;
import java.util.List;

import cg.contentdata.shared.ResourceData;
import cg.contentdata.shared.UIContentData;

public class ResourceDataClassChainStrategy implements IResourceDataClassStrategy
{
  public static final ResourceDataClassChainStrategy defaultInstance = new ResourceDataClassChainStrategy();
  
  private List< IResourceDataClassStrategy > lookupChain = new ArrayList< IResourceDataClassStrategy >();
  
  public ResourceDataClassChainStrategy()
  {
    initChain();
  }
  
  public void initChain()
  {
    lookupChain.add( ResourceDataClassAnnotationStrategy.defaultInstance );
    lookupChain.add( ResourceDataClassGenericStrategy.defaultInstance );
  }

  @Override
  @SuppressWarnings( "rawtypes" ) 
  public Class< ? extends ResourceData > getResourceDataClass( UIContentData contentData )
  {
    if( contentData.getResourceData() != null )
      return contentData.getResourceData().getClass();
    
    for( IResourceDataClassStrategy strategy : lookupChain )
    {
      Class< ? extends ResourceData > value = strategy.getResourceDataClass( contentData );
      if( value != null )
        return value;
    }
    return null;
  }

}
