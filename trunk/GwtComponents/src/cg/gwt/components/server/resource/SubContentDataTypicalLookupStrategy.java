package cg.gwt.components.server.resource;

import java.util.List;

import cg.gwt.components.annotation.IContentDataAttributes;
import cg.gwt.components.shared.data.UIContentData;

public class SubContentDataTypicalLookupStrategy implements ISubContentDataLookupStrategy
{
  private static SubContentDataTypicalLookupStrategy defaultInstance;
  
  public static SubContentDataTypicalLookupStrategy defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( SubContentDataTypicalLookupStrategy.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new SubContentDataTypicalLookupStrategy();
        }
      }
    }
    
    return defaultInstance;
  }

  @Override
  public List< UIContentData > getSubContentData( UIContentData contentData )
  {
    if( contentData == null )
      return null;
    
    Class< ? extends ISubContentDataLookupStrategy > lookupStrategy = null;
    Class< ? extends UIContentData > contentDataClass = contentData.getClass();
    IContentDataAttributes attributes = contentDataClass.getAnnotation( IContentDataAttributes.class );
    if( attributes != null )
    {
      if( !attributes.isComposite() )
        return null;
      lookupStrategy = attributes.subContentDataLookupStrategy();
    }
    
    if( lookupStrategy == null )
    {
      //don't have IContentDataAttributes annotation or doesn't specify the lookup strategy
      
    }
    //get contentData's @IContentDataAttributes
    return null;
  }

}
