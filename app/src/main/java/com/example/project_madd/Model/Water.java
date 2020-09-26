package com.example.project_madd.Model;

public class Water {

            private Integer userID;
            private String units;
            private Integer exerciseTime;
            private Integer totalAmount;
            private Integer drankAmount;
            private Integer remaining;

    public Water(Integer userID, String units, Integer exerciseTime, Integer totalAmount, Integer drankAmount, Integer remaining) {
        this.userID = userID;
        this.units = units;
        this.exerciseTime = exerciseTime;
        this.totalAmount = totalAmount;
        this.drankAmount = drankAmount;
        this.remaining = remaining;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Integer getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(Integer exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getDrankAmount() {
        return drankAmount;
    }

    public void setDrankAmount(Integer drankAmount) {
        this.drankAmount = drankAmount;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }
}
