package cg.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/*
 * the methods of Collection which need comparing are compared by instance instead of value
 * this util implements some methods by compare by value 
 */
public class CollectionUtil
{
  
  public static <T> boolean retainAllByValue( Collection<T> c1, Collection<? extends T> c2 )
  {
    if( c1 == null || c2 == null || c1.isEmpty() || c2.isEmpty() )
      return false;
    List< T > itemsToRemove = new ArrayList< T >();
    for( T item : c1 )
    {
      if( !containsByValue( c2, item ) )
        itemsToRemove.add( item );
    }
    c1.removeAll( itemsToRemove );
    return ( !itemsToRemove.isEmpty() );
  }
  
  public static <T> boolean addAllByValue( Collection<T> c1, Collection<? extends T> c2 )
  {
    if( c1 == null || c2 == null || c1.isEmpty() || c2.isEmpty() )
      return false;
    if( !( c1 instanceof Set ) )
      return c1.addAll( c2 );

    List< T > itemsToAdd = new ArrayList< T >();
    for( T item : c2 )
    {
      if( !containsByValue( c1, item ) )
        itemsToAdd.add( item );
    }
    return c1.addAll( itemsToAdd );

  }
  
  public static <T> boolean containsByValue( Collection< ? extends T > c, T item )
  {
    if( c == null || c.isEmpty() )
      return false;
    for( T i : c )
    {
      if( i.equals( item ) )
        return true;
    }
    return false;
  }
}
