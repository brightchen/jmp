package cg.gwt.components.client.ui.decorator;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public abstract class RubyTextDecorator< UC extends Widget, U extends Panel > extends Decorator< String, UC, U >
{
  public RubyTextDecorator( String rubyText, UC decoratedComponent )
  {
    super( rubyText, decoratedComponent );
  }
}
