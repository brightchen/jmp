package cg.gwt.components.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/*
 * UIGroup is a type of composite which composed by a group of same component
 * The data of the group is a list, I is the type of data of each item
 */
public abstract class UIGroup< I, U extends Panel > extends UIComposite< List<I>, U >
{
  public UIGroup( List<I> data, U container )
  {
    setData( data );
    setContainer( container );
  }
  
  @Override
  protected void beforeAddingChildren()
  {
    buildAndAddChildren( getData() );
  }
  
  /*
   * build and add children component into the children list
   * the build() method will add the component in the children list into the container
   */
  public void buildAndAddChildren( List<I> data )
  {
    if( data == null || data.isEmpty() )
      return;
    
    int index = 0;
    for( I childData : data )
    {
      addChildComponent( buildChildComponent( childData, index ), index );
      ++index;
    }
  }
  
  protected abstract Widget buildChildComponent( I childData, int index );
  
  @Override
  protected void addChildComponent( Widget child, int index )
  {
    getContainer().add( child );
  }

}
