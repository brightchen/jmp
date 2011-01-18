package cg.uia.renderers;

import java.io.Writer;

import cg.uia.components.RawText;
import cg.uia.renderer.ComponenetRenderer;

public class RawTextRenderer extends ComponenetRenderer< RawText >
{
  @Override
  public void render( Writer writer, RawText rawText )
  {
    try
    {
      writer.write( rawText.getData().getValue() );
    }
    catch( Exception e )
    {
      
    }
  }

}
