package cg.gwt.components.client.ui.decorator;

import cg.gwt.components.client.ui.ComponentUI;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RubyTextTopDecorator< UC extends ComponentUI< ?, ? > > extends RubyTextDecorator< UC, VerticalPanel >
{

  public RubyTextTopDecorator( String rubyText, UC decoratedComponent )
  {
    super( rubyText, decoratedComponent );
  }
  
  @Override
  protected VerticalPanel buildContainer()
  {
    VerticalPanel container = new VerticalPanel();
    container.add( new Label( getData() ) );
    return container;
  }
  
}
