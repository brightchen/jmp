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
    
    public void printDays()
    {
      System.out.println( "startDate: " + displayDate( startDate ) + "; endDate: " + displayDate( endDate ) + "; days: " + periodInDays() );
    }
    
    public String displayDate( Calendar date )
    {
      return date.get( Calendar.YEAR ) + "/" + date.get( Calendar.MONTH ) + 1 + "/" + date.get( Calendar.DAY_OF_MONTH );
    }
  }
  public static void main( String[] argv )
  {
    Period allPeriod;
    {
      Calendar startDate = Calendar.getInstance();
      startDate.set( 2008, 9, 30 );
      Calendar endDate = Calendar.getInstance();
      
      allPeriod = new Period( startDate, endDate );
    }

    Period leftPeriod1;
    {
      Calendar startDate = Calendar.getInstance();
      startDate.set( 2009, 6, 24 );
      Calendar endDate = Calendar.getInstance();
      endDate.set( 2009, 9, 27 );
      
      leftPeriod1 = new Period( startDate, endDate );
    }
    
    Period leftPeriod2;
    {
      Calendar startDate = Calendar.getInstance();
      startDate.set( 2009, 11, 07 );
      Calendar endDate = Calendar.getInstance();
      endDate.set( 2010, 0, 25 );
      
      leftPeriod2 = new Period( startDate, endDate );
    }

    Period leftPeriod3;
    {
      Calendar startDate = Calendar.getInstance();
      startDate.set( 2010, 6, 27 );
      Calendar endDate = Calendar.getInstance();
      endDate.set( 2010, 11, 23 );
      
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
    
    
    leftPeriod1.printDays();
    leftPeriod2.printDays();
    leftPeriod3.printDays();
    leftPeriod4.printDays();
  }
}
