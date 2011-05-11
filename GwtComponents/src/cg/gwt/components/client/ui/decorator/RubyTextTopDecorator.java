package cg.gwt.components.client.ui.decorator;

import cg.gwt.components.client.ui.UIComponent;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RubyTextTopDecorator< UC extends UIComponent< ?, ? > > extends RubyTextDecorator< UC, VerticalPanel >
{

  public RubyTextTopDecorator( String rubyText, UC decoratedComponent )
  {
    super( rubyText, decoratedComponent );
  }
  
  @Override
  protected VerticalPanel buildContainer()
  {
    VerticalPanel container = new VerticalPanel();
    applyDataToContainer( container );
    return container;
  }
  
  @Override
  protected VerticalPanel applyDataToContainer( VerticalPanel theContainer )
  {
    theContainer.add( new Label( getData() ) );
    return theContainer;
  }
}
