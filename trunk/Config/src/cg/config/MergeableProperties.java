package cg.config;

import java.util.Properties;

import cg.common.IMergeable;

public class MergeableProperties extends Properties implements IMergeable< Properties >
{
  private static final long serialVersionUID = 7563186399160158508L;

  @Override
  public void merge( Properties merging )
  {
    if( merging == null )
      return;
    this.putAll( merging );
  }

}
