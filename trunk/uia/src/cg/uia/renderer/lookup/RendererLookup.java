package cg.uia.renderer.lookup;

import cg.uia.api.IComponent;
import cg.uia.renderer.IComponentRenderer;

//
// the algrithem of this lookup as following:
// 1. search by the component instance 
// 2. search by the component type
// 3. use the default renderer with class: <Component> + Renderer, for example RawText --> RawTextRenderer
public class RendererLookup implements IRendererLookup
{
  private IRendererMatchStrategy lookupStrategy = RendererLookupStrategy.getInstance();
  
  private static RendererLookup instance;
  
  private RendererLookup(){}
  
  @Override
  public IComponentRenderer< ? > lookup( IComponent< ? > component )
  {
    return lookupStrategy.findMatchedRenderer( component );
  }

  public static RendererLookup getInstance()
  {
    if( instance != null )
    {
      return instance;
    }
    
    synchronized( RendererLookup.class )
    {
      if( instance != null )
        return instance;
      instance = new RendererLookup();
      return instance;
    }
  }

}
