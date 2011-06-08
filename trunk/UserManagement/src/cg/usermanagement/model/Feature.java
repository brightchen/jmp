package cg.usermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cg.model.api.INamedEntity;

@Entity
@Table(name="TFEATURE")
public class Feature implements INamedEntity
{
  @SequenceGenerator(name="TFEATURE_SEQ",sequenceName="TFEATURE_SEQ")
  @Id 
  @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO,generator="TFEATURE_SEQ")
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
