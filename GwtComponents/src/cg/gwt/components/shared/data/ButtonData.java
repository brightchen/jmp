package cg.gwt.components.shared.data;

import java.io.Serializable;

/*
 * the title and text have different meaning, see gwt Button
 * text: is the text display in the button
 * title: is the text displayed for help when mouse over
 */
public class ButtonData implements Serializable
{
  private static final long serialVersionUID = 4191914428310120094L;

  private String text;
  private String title;
  private boolean enabled = true;
  
  public ButtonData(){}
  
  public ButtonData( String text )
  {
    this( text, text );
  }
  public ButtonData( String text, String title )
  {
    setText( text );
    setTitle( title );
  }
  
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
  public boolean isEnabled()
  {
    return enabled;
  }
  public void setEnabled( boolean enabled )
  {
    this.enabled = enabled;
  }
  
  
}
