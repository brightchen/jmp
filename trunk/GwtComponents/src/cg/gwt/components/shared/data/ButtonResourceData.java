package cg.gwt.components.shared.data;

import java.io.Serializable;

public class ButtonResourceData extends ResourceData implements Serializable
{
  private static final long serialVersionUID = -5059566369895945697L;

  private String text;
  private String title;
  public String getText()
  {
    return text;
  }
  public void setText( String text )
  {
    this.text = text;
  }
  public String getTitle()
  {
    return title;
  }
  public void setTitle( String title )
  {
    this.title = title;
  }
  
}
