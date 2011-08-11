package cg.gwt.components.server.resource;

import java.util.List;

import cg.gwt.components.shared.data.UIContentData;

/*
 * If there are any getter/setter which annotated with IContentDataIndicator, delegate to the SubContentDataAnnotationLookupStrategy
 * else delegate to the SubContentDataTypeLookupStategy.
 * namely, it is not allowed to use both SubContentDataAnnotationLookupStrategy and SubContentDataTypeLookupStategy for one class
 */
public class SubContentDataDefaultLookupStrategy implements ISubContentDataLookupStrategy
{
  private static SubContentDataDefaultLookupStrategy defaultInstance;
  
  public static SubContentDataDefaultLookupStrategy defaultInstance()
  {
    if( defaultInstance == null )
    {
      synchronized( SubContentDataDefaultLookupStrategy.class )
      {
        if( defaultInstance == null )
        {
          defaultInstance = new SubContentDataDefaultLookupStrategy();
        }
      }
    }
    
    return defaultInstance;
  }


  @Override
  public List< UIContentData > getSubContentData( UIContentData contentData )
  {
    // IContentDataIndicator
    return null;
  }

}
