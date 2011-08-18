package cg.studio;

import java.util.Calendar;

public class DateCalculator
{
  public static class Period
  {
    private Calendar startDate;
    private Calendar endDate;
    
    public Period( Calendar startDate, Calendar endDate )
    {
      this.startDate = startDate;
      this.endDate = endDate;
    }
    
    public int periodInDays()
    {
      return (int)( ( endDate.getTimeInMillis() - startDate.getTimeInMillis() )/( 24 * 60 * 60 * 1000 ) );
    }
  }
  public static void main( String[] argv )
  {
    Period allPeriod;
    {
      Calendar startDate = Calendar.getInstance();
      startDate.set( 2008, 10, 30 );
      Calendar endDate = Calendar.getInstance();
      
      allPeriod = new Period( startDate, endDate );
    }

    Period leftPeriod1;
    {
      Calendar startDate = Calendar.getInstance();
      startDate.set( 2009, 07, 24 );
      Calendar endDate = Calendar.getInstance();
      endDate.set( 2009, 10, 27 );
      
      leftPeriod1 = new Period( startDate, endDate );
    }
    
    Period leftPeriod2;
    {
      Calendar startDate = Calendar.getInstance();
      startDate.set( 2009, 12, 07 );
      Calendar endDate = Calendar.getInstance();
      endDate.set( 2010, 01, 25 );
      
      leftPeriod2 = new Period( startDate, endDate );
    }

    Period leftPeriod3;
    {
      Calendar startDate = Calendar.getInstance();
      startDate.set( 2010, 07, 27 );
      Calendar endDate = Calendar.getInstance();
      endDate.set( 2010, 12, 23 );
      
      leftPeriod3 = new Period( startDate, endDate );
    }

    Period leftPeriod4;
    {
      Calendar startDate = Calendar.getInstance();
      startDate.set( 2011, 03, 19 );
      Calendar endDate = Calendar.getInstance();
      endDate.set( 2011, 04, 30 );
      
      leftPeriod4 = new Period( startDate, endDate );
    }

    int totalLeftInDays = leftPeriod1.periodInDays() + leftPeriod2.periodInDays() + leftPeriod3.periodInDays() 
                        + leftPeriod4.periodInDays();
    int totalPeriodInDays = allPeriod.periodInDays();
    int stayInDays = totalPeriodInDays - totalLeftInDays;
    
    System.out.println( "total days: " + totalPeriodInDays + "; total left days: " + totalLeftInDays + "; stayed days: " + stayInDays );
    
  }
}
