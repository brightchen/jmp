package cg.studio;

public enum Singleton
{
  INSTANCE;
  public static Singleton getInstance()
  {
    return INSTANCE;
  }

  public void sss()
  {
    System.out.println( "sss" );
  }
}