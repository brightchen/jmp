package cg.uia.renderer;

import java.io.Writer;
import cg.uia.api.IComponent;

public interface IComponentRenderer< T extends IComponent< ? > >
{
  public void render( Writer writer, T component );
}
