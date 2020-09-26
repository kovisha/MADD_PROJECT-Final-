package com.example.project_madd.Model;

import java.util.Date;

public class PeriodTracker {

    private String startDate;
    private String endDate;
    private Integer BleedingDays;
    private String nextStartDate;
    private Integer periodLength;
    private Integer cycleLength;
    private Integer ovulationLength;

    public PeriodTracker(String startDate, String endDate, Integer bleedingDays, String nextStartDate, Integer periodLength, Integer cycleLength, Integer ovulationLength) {
        this.startDate = startDate;
        this.endDate = endDate;
        BleedingDays = bleedingDays;
        this.nextStartDate = nextStartDate;
        this.periodLength = periodLength;
        this.cycleLength = cycleLength;
        this.ovulationLength = ovulationLength;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {

        this.startDate = startDate;
    }

    public String getEndDate() {

        return endDate;

    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getBleedingDays() {
        return BleedingDays;
    }

    public void setBleedingDays(Integer bleedingDays) {
        BleedingDays = bleedingDays;
    }

    public String getNextStartDate() {
        return nextStartDate;
    }

    public void setNextStartDate(String nextStartDate) {
        this.nextStartDate = nextStartDate;
    }

    public Integer getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(Integer periodLength) {
        this.periodLength = periodLength;
    }

    public Integer getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(Integer cycleLength) {
        this.cycleLength = cycleLength;
    }

    public Integer getOvulationLength() {
        return ovulationLength;
    }

    public void setOvulationLength(Integer ovulationLength) {
        this.ovulationLength = ovulationLength;
    }
}
