package cg.usermanagement.model.view;

import cg.model.api.IReadableModelView;
import cg.usermanagement.model.Role;

public class RoleView implements IReadableModelView< Role >
{
  private Role entity;

  public RoleView()
  {
    entity = new Role();
  }

  public RoleView( Role role )
  {
    entity = role;
  }

  @Override
  public Role getEntity()
  {
    return entity;
  }

  @Override
  public void getValuesFromEntity()
  {
  }

  public Long getId()
  {
    return entity.getId();
  }
  public void setId( long id )
  {
    if( getId() != null && getId() > 0 )
      throw new IllegalStateException( "the stored Role is not suppose to change the Id." );
    entity.setId( id );
  }
  
  public String getName()
  {
    return entity.getName();
  }
  public void setName( String name )
  {
    entity.setName( name );
  }
}
