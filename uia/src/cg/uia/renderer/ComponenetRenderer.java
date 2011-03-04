package cg.uia.renderer;

import java.io.Writer;

import cg.uia.api.IComponent;

// all the component renderer should be thread-safe
public class ComponenetRenderer< T extends IComponent< ? > > implements IComponentRenderer< T >
{
  @Override
  public void render( Writer writer, T component )
  {
    // do nothing
  }

}
