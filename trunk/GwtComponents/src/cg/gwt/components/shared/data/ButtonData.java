package cg.gwt.components.shared.data;

import java.io.Serializable;

public class ButtonData implements Serializable
{
  private static final long serialVersionUID = 4191914428310120094L;

  private String title;
  private boolean enabled = true;
  
  public String getTitle()
  {
    return title;
  }
  public void setTitle( String title )
  {
    this.title = title;
  }
  public boolean isEnabled()
  {
    return enabled;
  }
  public void setEnabled( boolean enabled )
  {
    this.enabled = enabled;
  }
  
  
}
