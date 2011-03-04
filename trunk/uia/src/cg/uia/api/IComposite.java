package cg.uia.api;

import java.util.List;

//the Composite( with data type D ) should be extended from IComponent( with data type D )
//and it can contain component of any data type
public interface IComposite< D extends IComponentData > extends IComponent< D >
{
  public List< IComponent<?> > getSubComponents();
  public void addComponent( IComponent<?> component );
  //insert insertingComponent before component
//  public void insertComponent( IComponent insertingComponent, IComponent component );
  //insert insertingComponent at position <position>
  public void insertComponent( IComponent<?> insertingComponent, int position );
}
