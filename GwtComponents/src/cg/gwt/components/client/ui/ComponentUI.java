package cg.gwt.components.client.ui;


import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/*
 * UIComponent is a type of Widget which can build by its data
 * U is the Widget we want to build
 * in order to load U, we wrapper U by SimplePanel and load SimplePanel
 */
public abstract class ComponentUI< D, U extends Widget > extends SimplePanel implements IDataSupport< D >
{
  private D data;
  
  @Override
  public void setData( D data )
  {
    this.data = data;
  }

  @Override
  public D getData()
  {
    return data;
  }

  public abstract U build(); 

  //this method should be called after calling build()
  //return the real component which is wrappped by panel
  public abstract U getRealComponent();
  
  @Override
  public void onLoad()
  {
    //wrapper by Panel
    add( build() );
  }

}
