package cg.usermanagement.gwt.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Command;

//As the TypicalCommand is in the client side, use String a parameter type
public class TypicalCommand implements Command
{
  private String key;
  private List< String > parameters;
  
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
