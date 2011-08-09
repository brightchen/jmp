package cg.gwt.components.shared.data;

import java.io.Serializable;

/*
 * the title and text have different meaning, see gwt Button
 * text: is the text display in the button
 * title: is the text displayed for help when mouse over
 */
public class ButtonData extends UIContentData< ButtonResourceData > implements Serializable
{
  private static final long serialVersionUID = 4191914428310120094L;

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
  
  public ButtonResourceData getOrCreateResourceData()
  {
    ButtonResourceData resourceData = getResourceData();
    if( resourceData == null )
    {
      resourceData = new ButtonResourceData();
      setResourceData( resourceData );
    }
    return resourceData;
  }
  
  public String getText()
  {
    return getResourceData() == null ? null : getResourceData().getText();
  }
  public void setText( String text )
  {
    getOrCreateResourceData().setText( text );
  }
  
  public String getTitle()
  {
    return getResourceData() == null ? null : getResourceData().getTitle();
  }
  public void setTitle( String title )
  {
    getOrCreateResourceData().setTitle( title );
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
