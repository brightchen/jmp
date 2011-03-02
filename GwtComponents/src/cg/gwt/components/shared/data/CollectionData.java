package cg.gwt.components.shared.data;

import java.util.Collection;

//this interface represents a collection of UIObjectData
public interface CollectionData< T extends IUIObjectData > extends IUIObjectData, Collection<T>
{
}
