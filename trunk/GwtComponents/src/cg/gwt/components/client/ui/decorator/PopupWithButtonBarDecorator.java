package cg.gwt.components.client.ui.decorator;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PopupWithButtonBarDecorator< UB extends Widget, UC extends Widget > extends PopupDecorator< String, UC >
{
  private UB buttonBar;
  private Panel middlePanel;    //this is the panel which the decoratedComponent and buttonBar will be added as the popup panel is a SimplePanel

  public PopupWithButtonBarDecorator( String title, UC decoratedComponent )
  {
    super( title, decoratedComponent );
  }

  public PopupWithButtonBarDecorator( String title, UB buttonBar, UC decoratedComponent )
  {
    this( title, decoratedComponent );
    setButtonBar( buttonBar );
  }

  @Override
  protected PopupPanel buildDecoratorComponent()
  {
    PopupPanel popup = new PopupPanel();
    popup.setTitle( getData() );
    return popup;
  }
  
  protected void buildMiddlePanel()
  {
    middlePanel = new VerticalPanel();
  }

  /*
   * create the middle panel before add child components into it
   * @see cg.gwt.components.client.ui.UIComposite#beforeAddingChildren()
   */
  @Override
  protected void beforeAddingChildren()
  {
    buildMiddlePanel();
  }
  
  /*
   * instead add child components to Container directly, add child components to middlePanel;
   * @see cg.gwt.components.client.ui.UIComposite#addChildComponent(com.google.gwt.user.client.ui.Widget, int)
   */
  @Override
  protected void addChildComponent( Widget child, int index )
  {
    middlePanel.add( child );
  }

  /*
   * add the buttonBar and add the middle panel to the container
   * @see cg.gwt.components.client.ui.UIComposite#afterAddingChildren()
   */
  @Override
  protected void afterAddingChildren()
  {
    middlePanel.add( buttonBar );
    getDecoratorComponent().add( middlePanel );
  }
  
  public UB getButtonBar()
  {
    return buttonBar;
  }

  public void setButtonBar( UB buttonBar )
  {
    this.buttonBar = buttonBar;
  }
}
