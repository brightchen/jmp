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
public class Role implements INamedEntity
{
  @SequenceGenerator( name = "TROLE_SEQ" , sequenceName = "TROLE_SEQ")
  @Id
  @GeneratedValue( strategy = javax.persistence.GenerationType.AUTO , generator = "TROLE_SEQ")
  @Column( name = "ID" )
  private Long   id;

  @Column( name = "NAME", length = 50, nullable = false, unique = true, updatable = true )
  private String name;

  @ManyToMany( mappedBy="roles" )
  private Set< Account > accounts;
  
  @ManyToMany
  @JoinTable( name="TROLE_PERMISSION" )
  private Set< Permission > permissions;
  
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

  public Set< Account > getAccounts()
  {
    return accounts;
  }

  public void setAccounts( Set< Account > accounts )
  {
    this.accounts = accounts;
  }

  public Set< Permission > getPermissions()
  {
    return permissions;
  }

  public void setPermissions( Set< Permission > permissions )
  {
    this.permissions = permissions;
  }
  
}
