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
  
  private Map< IComponent<?>, IComponentRenderer<?> > componentsRenderersMap = new HashMap< IComponent<?>, IComponentRenderer<?> >();
  
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

  //the map definition itself can't make the restriction of component type and render type,
  @SuppressWarnings( "unchecked" )
  @Override
  public < T extends IComponent< ? >> IComponentRenderer< T > findMatchedRenderer( T component )
  {
    return (IComponentRenderer< T >)componentsRenderersMap.get( component );
  }
  
  //the map definition itself can't make the restriction of component type and render type,
  //the method which add the entry to the map should be type-safe
  public < T extends IComponent< ? >> void addComponentRenderer( T component, IComponentRenderer<T> renderer )
  {
    componentsRenderersMap.put( component, renderer );
  }
}
