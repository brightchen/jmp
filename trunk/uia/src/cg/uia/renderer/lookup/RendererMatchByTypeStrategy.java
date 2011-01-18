package cg.uia.renderer.lookup;

import java.util.HashMap;
import java.util.Map;

import cg.uia.api.IComponent;
import cg.uia.renderer.IComponentRenderer;

//search by the component type
//FIXME: there are potential thread-safe problem for adding/modifying entry to the map and finding 
public class RendererMatchByTypeStrategy implements IRendererMatchStrategy
{
  private static RendererMatchByTypeStrategy instance;
  
  private Map< Class< ? extends IComponent >, IComponentRenderer > componentTypesRenderersMap 
      = new HashMap< Class< ? extends IComponent >, IComponentRenderer >();

  public static RendererMatchByTypeStrategy getInstance()
  {
    if( instance != null )
    {
      return instance;
    }
    
    synchronized( RendererMatchByTypeStrategy.class )
    {
      if( instance != null )
        return instance;
      instance = new RendererMatchByTypeStrategy();
      return instance;
    }
  }

  private RendererMatchByTypeStrategy()
  {
  }

  @Override
  public < T extends IComponent< ? > > IComponentRenderer< T > findMatchedRenderer( T component )
  {
    return componentTypesRenderersMap.get( component.getClass() );
  }

  public void addComponentRenderer( Class< ? extends IComponent > componentType, IComponentRenderer renderer )
  {
    componentTypesRenderersMap.put( componentType, renderer );
  }

}
