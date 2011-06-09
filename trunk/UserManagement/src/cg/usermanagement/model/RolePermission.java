package cg.usermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cg.model.api.IEntity;

/*
 * This is a relationship table between Role and Permission( Feature, Operation )
 */

@Entity
@Table( name = "TRole")
public class RolePermission implements IEntity
{
  @SequenceGenerator( name = "TROLEPERMISSION_SEQ" , sequenceName = "TROLEPERMISSION_SEQ")
  @Id
  @GeneratedValue( strategy = javax.persistence.GenerationType.AUTO , generator = "TROLEPERMISSION_SEQ")
  @Column( name = "ID" )
  private Long   id;
  
  @ManyToOne( fetch=FetchType.LAZY ) 
  @JoinColumn( name="ROLE_ID" ) 
  private Role role;
  
  @OneToOne(optional=false)
  @JoinColumn( name="FEATURE_ID", unique=true, nullable=false, updatable=false )
  private Feature feature;

  @OneToOne(optional=false)
  @JoinColumn( name="OPERATION_ID", unique=true, nullable=false, updatable=false )
  private Operation operation;

  public Long getId()
  {
    return id;
  }

  public void setId( Long id )
  {
    this.id = id;
  }

  public Role getRole()
  {
    return role;
  }

  public void setRole( Role role )
  {
    this.role = role;
  }

  public Feature getFeature()
  {
    return feature;
  }

  public void setFeature( Feature feature )
  {
    this.feature = feature;
  }

  public Operation getOperation()
  {
    return operation;
  }

  public void setOperation( Operation operation )
  {
    this.operation = operation;
  }
}
