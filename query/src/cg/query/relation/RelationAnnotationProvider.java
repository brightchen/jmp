package cg.query.relation;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public interface RelationAnnotationProvider
{
  public OneToOne getOneToOne();
  public OneToMany getOneToMany();
  public ManyToOne getManyToOne();
  public ManyToMany getManyToMany();
}
