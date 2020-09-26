package com.example.project_madd.IT19080840;


import com.example.project_madd.DisplayCalendar;
import com.example.project_madd.DisplayStartDateHome;
import com.example.project_madd.EndPeriodActivity;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/****************************IT19080840 PERIOD TRACKER TEST  CASES*************************************************************/
public class PeriodTracker {

    private EndPeriodActivity endPeriodActivity;
    private DisplayStartDateHome displayStartDateHome;
    private DisplayCalendar displayCalendar;

    @Before
    public void setup() {
        endPeriodActivity = new EndPeriodActivity();
        displayStartDateHome = new DisplayStartDateHome();
        displayCalendar = new DisplayCalendar();
    }

    /***********************Calculate the actual bleeding days****************************************************************/
    @Test
    public void bleeding_Correct() throws ParseException {
        long bleedingDays = endPeriodActivity.calculateMyPeriod("2020-10-1", "2020-10-09");
        assertEquals(8, bleedingDays);
    }

    /***********************Calculate prediction of the next Menstrual Cycle start Date****************************************/
    @Test
    public void nextPeriodDate_Correct() {
        String nextPeriod = displayStartDateHome.nextStartDate("2020-10-1", 28);
        assertEquals("2020-10-29", nextPeriod);

    }

    /***********************Calculate the expected period end date to compare with actual end date****************************
     to predict mal function of periodic cycle*****************************************************************************/
    @Test
    public void expectedEndDate_Correct() {
        String endDate = displayCalendar.expectedPeriodEndDate("2020-10-01", 5);
        assertEquals("2020-10-06", endDate);
    }
}

