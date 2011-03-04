package cg.gwt.components.client.ui;

import com.google.gwt.user.client.ui.UIObject;

// Transformer is the combination of Builder and Digester
public interface IWidgetTransformer< W extends UIObject > extends IUIObjectBuilder<W>, IUIObjectDigester
{
}
