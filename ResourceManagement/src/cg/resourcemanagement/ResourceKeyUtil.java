package cg.resourcemanagement;

import cg.common.util.StringUtil;

public class ResourceKeyUtil
{
  /*
   * return the preferkey
   */
  public static ResourceKey mergeResourceKey( ResourceKey preferKey, ResourceKey key )
  {
    if( preferKey == null )
      throw new IllegalArgumentException( "the preferKey should not null" );
    
    if( key == null )
      return preferKey;
    
    // the previous value is over the current value
    if( StringUtil.isNullOrEmpty( preferKey.getModuleName() ) && !StringUtil.isNullOrEmpty( key.getModuleName() ) )
      preferKey.setModuleName( key.getModuleName() );
    if( StringUtil.isNullOrEmpty( preferKey.getClassName() ) && !StringUtil.isNullOrEmpty( key.getClassName() ) )
      preferKey.setClassName( key.getClassName() );
    if( StringUtil.isNullOrEmpty( preferKey.getPropertyName() ) && !StringUtil.isNullOrEmpty( key.getPropertyName() ) )
      preferKey.setPropertyName( key.getPropertyName() );

    return preferKey;
  }

  
  public static boolean isValidKey( ResourceKey key )
  {
    return ( key != null && key.isValid() );
  }

}
