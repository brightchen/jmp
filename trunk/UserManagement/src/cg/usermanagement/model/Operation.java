package cg.usermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cg.model.api.INamedEntity;

@Entity
@Table(name="TOPERATION")
public class Operation implements INamedEntity
{
  @SequenceGenerator(name="TOPERATION_SEQ",sequenceName="TOPERATION_SEQ")
  @Id 
  @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO,generator="TOPERATION_SEQ")
  @Column(name="ID")
  private Long id;
  
  @Column( name="NAME", length = 50, nullable = false, unique = true, updatable = true )
  private String name;

  public Long getId()
  {
    return id;
  }

  public void setId( Long id )
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

}
