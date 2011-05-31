package cg.studio.log4j;

import org.apache.log4j.Logger;

public class Log4jTester
{
  private static org.apache.log4j.Logger log = Logger.getLogger( Log4jTester.class );

  public static void main( String[] args )
  {
    log.trace( "Trace" );
    log.debug( "Debug" );
    log.info( "Info" );
    log.warn( "Warn" );
    log.error( "Error" );
    log.fatal( "Fatal" );

  }
}
