package cg.gwt.components.client.ui;

import cg.gwt.components.shared.data.PartData;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class TypicalCompositePart< D1 extends PartData, W1 extends Widget, P1 extends Part< D1, W1 >,
                                   D2 extends PartData, W2 extends Widget, P2 extends Part< D2, W2 >, 
                                   P extends Panel >  extends CompositePart< D1, W1, P1, D2, W2, P2, P >
{
  private ICompositeBuilder< P > compositeBuilder;
  private P container;
  
  public TypicalCompositePart( P1 part1, P2 part2 )
  {
    this( part1, part2, null );
  }
  
  public TypicalCompositePart( P1 part1, P2 part2, ICompositeBuilder< P > compositeBuilder )
  {
    setPart1( part1 );
    setPart2( part2 );
    setCompositeBuilder( compositeBuilder );
  }
  
  @Override
  public P build()
  {
    ICompositeBuilder< P > theBuilder = ( compositeBuilder == null ? new SimpleCompositeBuilder<P>() : compositeBuilder );
    return theBuilder.build( container, getPart1().build(), getPart2().build() );
  }

  public ICompositeBuilder<P> getCompositeBuilder()
  {
    return compositeBuilder;
  }

  public void setCompositeBuilder( ICompositeBuilder<P> compositeBuilder )
  {
    this.compositeBuilder = compositeBuilder;
  }

  public P getContainer()
  {
    return container;
  }

  public void setContainer( P container )
  {
    this.container = container;
  }
}
