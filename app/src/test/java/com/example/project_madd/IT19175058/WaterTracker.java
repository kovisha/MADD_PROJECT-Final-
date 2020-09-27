package com.example.project_madd.IT19175058;

import com.example.project_madd.amount_selection;
import com.example.project_madd.water_calculation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WaterTracker {

    /******************************* test case for calculating the total amount needed ******************************************/
    private water_calculation waterCal;

    @Before
    public void setup(){
        waterCal = new water_calculation();
    }

    @Test
    public void total_isCorrect(){
        Double results = waterCal.total(45,30);
        assertEquals(1850,results,0.001);
    }

    /****************************************** test case for calculating drank amounts and remaining amounts ************************************/
    private amount_selection amtSelection;

    @Before
    public void setup2(){
        amtSelection = new amount_selection();
    }

    //drank amount check
    @Test
    public void calcDrank_isCorrect(){
        Double results = amtSelection.calcDrank(0.00,500.00);
        assertEquals(500.00,results,0.001);
    }

    //remaining amount check
    @Test
    public void calcRemaining_isCorrect(){
        Double results = amtSelection.calRemaining(1850.00,500.00);
        assertEquals(1350.00,results,0.001);
    }



}
