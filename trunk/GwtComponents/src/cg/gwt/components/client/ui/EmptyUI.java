package cg.gwt.components.client.ui;

import com.google.gwt.user.client.ui.SimplePanel;

public class EmptyUI extends SimplePanel
{
  private static EmptyUI instance;
  
  public static EmptyUI instance()
  {
    if( instance == null )
    {
      instance = new EmptyUI();
    }
    return instance;
  }
}
