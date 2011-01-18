package cg.uia.renderer.lookup;

import cg.uia.api.IComponent;
import cg.uia.renderer.IComponentRenderer;

public interface IRendererMatchStrategy
{
  public < T extends IComponent<?> > IComponentRenderer< T > findMatchedRenderer( T component );
}
