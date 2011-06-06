package cg.usermanagement.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cg.model.api.INamedEntity;

@Entity
@Table( name = "TRole")
public class Permission implements INamedEntity
{
  @SequenceGenerator( name = "TPERMISSION_SEQ" , sequenceName = "TPERMISSION_SEQ")
  @Id
  @GeneratedValue( strategy = javax.persistence.GenerationType.AUTO , generator = "TPERMISSION_SEQ")
  @Column( name = "ID")
  private Long   id;

  @Column( name = "NAME", length = 50, nullable = false, unique = true, updatable = true )
  private String name;

  @ManyToMany
  @JoinTable( name="TPERM_FEATURE" )
  private Set< Feature > features;

  @ManyToMany
  @JoinTable( name="TPERM_OPERATION" )
  private Set< Operation > operations;

  @Column( name = "DESC", length = 250 )
  private String desc;


  
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

  public String getDesc()
  {
    return desc;
  }

  public void setDesc( String desc )
  {
    this.desc = desc;
  }
}
