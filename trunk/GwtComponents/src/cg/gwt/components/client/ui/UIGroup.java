package cg.gwt.components.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.Panel;

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
    
    buildAndAddChildren( data );
  }
  
  /*
   * build and add children
   */
  public void buildAndAddChildren( List<I> data )
  {
    if( data == null || data.isEmpty() )
      return;
    
    int index = 0;
    for( I childData : data )
    {
      buildAndAddChild( childData, index++ );
    }
  }
  
  /*
   * build the child and add it into the children using addChild()
   */
  public abstract void buildAndAddChild( I childData, int index );

}