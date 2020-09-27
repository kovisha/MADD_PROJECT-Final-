package com.example.project_madd.IT19162010;

import com.example.project_madd.CommonAttributesActivity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BmiTracker {

    /************* test for common Attributes************/
    private CommonAttributesActivity commonAttributesActivity ;

    @Before
    public void setup(){

        commonAttributesActivity = new CommonAttributesActivity();

    }

    @Test
    public void BMI_iscorrect(){

        float results  = commonAttributesActivity.BMIcalc(155,55);
        assertEquals(22.892820358276367,results,0.001);
    }



    @Test
    public void BMRmen_iscorrect(){

        float results = commonAttributesActivity.BMRforMen(55,155,22);
        assertEquals(1413.75,results,0.001);

    }

    @Test
    public void BMRwomen_iscorrect(){

        float results = commonAttributesActivity.BMRforwoMen(55,155,22);
        assertEquals(1450.35,results,0.001);

    }

    @Test
    public void WTH_iscorrect(){
        float results = commonAttributesActivity.WTHcalc(155,55);
        assertEquals(35.4838,results,0.001);


    }
}
