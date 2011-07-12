package cg.common.util;

/*
 * this class provides setter/getter data, can be used like the reference of C++
 */
public class DataReference<T>
{
  private T data;

  public T getData()
  {
    return data;
  }
  public void setData( T data )
  {
    this.data = data;
  }
}
