package cg.common.util;

public class StringUtil
{
  public static String nullToEmpty( String str )
  {
    return ( str == null ) ? "" : str;
  }
  
  public static boolean isNullOrEmpty( String str )
  {
    return ( str == null || str.length() == 0 );
  }
}
