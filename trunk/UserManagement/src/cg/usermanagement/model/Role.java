package cg.usermanagement.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import cg.model.api.INamedEntity;

@Entity
@Table( name = "TRole")
public class Role implements INamedEntity
{
  @javax.persistence.SequenceGenerator( name = "TROLE_SEQ" , sequenceName = "TROLE_SEQ")
  @javax.persistence.Id
  @javax.persistence.GeneratedValue( strategy = javax.persistence.GenerationType.AUTO , generator = "TACCOUNT_SEQ")
  @javax.persistence.Column( name = "ID")
  private Long   id;

  @javax.persistence.Column( name = "NAME")
  private String name;

  @Override
  public Long getId()
  {
    return id;
  }

  @Override
  public void setId( Long id )
  {
    this.id = id;
  }

  @Override
  public String getName()
  {
    return name;
  }

  @Override
  public void setName( String name )
  {
    this.name = name;
  }
}
