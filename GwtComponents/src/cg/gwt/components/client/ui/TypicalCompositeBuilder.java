package cg.gwt.components.client.ui;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class TypicalCompositeBuilder< D1, W1 extends Widget, B1 extends UIObjectBuilder< D1, W1 >,
                                      D2, W2 extends Widget, B2 extends UIObjectBuilder< D2, W2 >, 
                                      C extends Panel >  extends CompositeBuilder< D1, W1, B1, D2, W2, B2, C >
{
  private ICompositeBuilder< C > compositeBuilder;
  private C container;
  
  public TypicalCompositeBuilder( B1 builder1, B2 builder2 )
  {
    this( builder1, builder2, null );
  }
  
  public TypicalCompositeBuilder( B1 builder1, B2 builder2, ICompositeBuilder< C > compositeBuilder )
  {
    setBuilder1( builder1 );
    setBuilder2( builder2 );
    setCompositeBuilder( compositeBuilder );
  }
  
  @Override
  public C build()
  {
    ICompositeBuilder< C > theBuilder = ( compositeBuilder == null ? new SimpleCompositeBuilder<C>() : compositeBuilder );
    return theBuilder.build( container, getBuilder1().build(), getBuilder2().build() );
  }

  //simplly refresh the sub widgets
  @Override
  public void refreshWidget()
  {
    if( getBuilder1() != null )
      getBuilder1().refreshWidget();
    if( getBuilder2() != null )
      getBuilder2().refreshWidget();
  }
  
  public ICompositeBuilder<C> getCompositeBuilder()
  {
    return compositeBuilder;
  }

  public void setCompositeBuilder( ICompositeBuilder<C> compositeBuilder )
  {
    this.compositeBuilder = compositeBuilder;
  }

  public C getContainer()
  {
    return container;
  }

  public void setContainer( C container )
  {
    this.container = container;
  }
}
