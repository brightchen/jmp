package cg.gwt.components.client.ui;

import cg.gwt.components.shared.data.PartData;

import com.google.gwt.user.client.ui.Widget;

//this kind of part will not load itself during the build
//it delay the loading when the client call the load()
public abstract class LazyLoadingPart< T extends PartData, W extends Widget > extends Part< T, W >
{
  public abstract void load();
}
