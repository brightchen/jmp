package cg.uia.renderer.lookup;

import cg.uia.api.IComponent;
import cg.uia.renderer.IComponentRenderer;

//
//the algrithem of this lookup as following:
//1. search by the component instance 
//2. search by the component type
//3. use the default renderer with class: <Component> + Renderer, for example RawText --> RawTextRenderer
//
public class RendererLookupStrategy implements IRendererMatchStrategy
{
  private static RendererLookupStrategy instance;
  private static IRendererMatchStrategy matchStrategies[] = 
  {
    RendererMatchByInstanceStrategy.getInstance(),
    RendererMatchByTypeStrategy.getInstance(),
    DefaultRendererMatchStrategy.getInstance()
  };
  
  private RendererLookupStrategy()
  {
  }
  public static RendererLookupStrategy getInstance()
  {
    if( instance != null )
    {
      return instance;
    }
    
    synchronized( RendererLookupStrategy.class )
    {
      if( instance != null )
        return instance;
      instance = new RendererLookupStrategy();
      return instance;
    }
  }

  public < T extends IComponent<?> > IComponentRenderer< T > findMatchedRenderer( T component )
  {
    for( IRendererMatchStrategy matchStrategy : matchStrategies )
    {
      IComponentRenderer< T > renderer = matchStrategy.findMatchedRenderer( component );
      if( renderer != null )
        return renderer;
    }
    return null;
  }
}
