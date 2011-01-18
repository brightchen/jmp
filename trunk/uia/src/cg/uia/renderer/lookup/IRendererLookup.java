package cg.uia.renderer.lookup;

import cg.uia.api.IComponent;
import cg.uia.renderer.IComponentRenderer;

public interface IRendererLookup
{
  public IComponentRenderer< ? > lookup( IComponent< ? > component );
}
