package com.example.project_madd;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private EndPeriodActivity endPeriodActivity;
    private DisplayStartDateHome displayStartDateHome;
    private DisplayCalendar displayCalendar;

    @Before
    public void setup(){
        endPeriodActivity = new EndPeriodActivity();
        displayStartDateHome = new DisplayStartDateHome();
        displayCalendar = new DisplayCalendar();
    }

    /***********************Calculate the actual bleeding days****************************************************************/
    @Test
   public void bleeding_Correct() throws ParseException {
        long bleedingDays = endPeriodActivity.calculateMyPeriod("2020-10-1","2020-10-09");
        assertEquals(8,bleedingDays);
    }

    /***********************Calculate prediction of the next Menstrual Cycle start Date****************************************/
    @Test
    public void nextPeriodDate_Correct(){
        String nextPeriod = displayStartDateHome.nextStartDate("2020-10-1",28);
        assertEquals("2020-10-29",nextPeriod);

    }

    /***********************Calculate the expected period end date to compare with actual end date****************************
     to predict mal function of periodic cycle*****************************************************************************/
    @Test
    public void expectedEndDate_Correct(){
        String endDate = displayCalendar.expectedPeriodEndDate("2020-10-01",5);
        assertEquals("2020-10-06",endDate);
    }



}