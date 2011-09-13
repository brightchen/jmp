package cg.query.relation;

/*
 * WholeEntityNetwork is an EntityNetwork which includes all connected entities at a certain time.
 * namely, the WholeEntityNetwork can't be and more entity into it.
 * A project may have multiple separated WholeEntityNetwork
 */
public class WholeEntityNetwork extends EntityNetwork
{
  private String name;

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }
  
  
}
