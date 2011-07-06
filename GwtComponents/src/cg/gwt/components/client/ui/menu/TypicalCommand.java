package cg.gwt.components.client.ui.menu;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Command;

//As the TypicalCommand is in the client side, use String a parameter type
public class TypicalCommand implements Command
{
  private String key;
  private List< String > parameters;
  
  public TypicalCommand()
  {
    this( null );
  }

  public TypicalCommand( String key )
  {
    setKey( key );
  }


  @Override
  public void execute()
  {
  }

  public String getKey()
  {
    return key;
  }

  public void setKey( String key )
  {
    this.key = key;
  }

  public List< String > getParameters()
  {
    return parameters;
  }

  public void setParameters( List< String > parameters )
  {
    this.parameters = parameters;
  }

  public void addParameter( String parameter )
  {
    if( parameters == null )
      parameters = new ArrayList< String >();
    parameters.add( parameter );
  }
}
