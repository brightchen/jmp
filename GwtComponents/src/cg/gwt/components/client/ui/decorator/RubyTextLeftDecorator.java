package cg.gwt.components.client.ui.decorator;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RubyTextLeftDecorator< UC extends Widget > extends RubyTextDecorator< UC, HorizontalPanel >
{

  public RubyTextLeftDecorator( String rubyText, UC decoratedComponent )
  {
    super( rubyText, decoratedComponent );
  }
  
  @Override
  protected HorizontalPanel buildContainer()
  {
    HorizontalPanel container = new HorizontalPanel();
    applyDataToContainer( container );
    return container;
  }
  
  @Override
  protected HorizontalPanel applyDataToContainer( HorizontalPanel theContainer )
  {
    theContainer.add( new Label( getData() ) );
    return theContainer;
  }
}