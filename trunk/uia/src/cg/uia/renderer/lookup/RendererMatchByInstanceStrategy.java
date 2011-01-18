package cg.uia.renderer.lookup;

import java.util.HashMap;
import java.util.Map;

import cg.uia.api.IComponent;
import cg.uia.renderer.IComponentRenderer;

// search by the component instance
// FIXME: there are potential thread-safe problem for adding/modifying entry to the map and finding 
public class RendererMatchByInstanceStrategy implements IRendererMatchStrategy
{
  private static RendererMatchByInstanceStrategy instance;
  
  private Map< IComponent, IComponentRenderer > componentsRenderersMap = new HashMap< IComponent, IComponentRenderer >();
  
  public static RendererMatchByInstanceStrategy getInstance()
  {
    if( instance != null )
    {
      return instance;
    }
    
    synchronized( RendererMatchByInstanceStrategy.class )
    {
      if( instance != null )
        return instance;
      instance = new RendererMatchByInstanceStrategy();
      return instance;
    }
  }

  private RendererMatchByInstanceStrategy()
  {
  }

  @Override
  public < T extends IComponent< ? >> IComponentRenderer< T > findMatchedRenderer( T component )
  {
    return componentsRenderersMap.get( component );
  }
  
  public void addComponentRenderer( IComponent component, IComponentRenderer renderer )
  {
    componentsRenderersMap.put( component, renderer );
  }
}
