package com.example.project_madd.Model;

public class Water {


    //Water Class Attributes
    private Integer weight;
    private Integer exerciseTime;
    private Double total;
    private Double drank;
    private Double remaining;


    //Overloaded constructor
    public Water(Integer weight, Integer exerciseTime, Double total, Double drank, Double remaining) {
        this.weight = weight;
        this.exerciseTime = exerciseTime;
        this.total = total;
        this.drank = drank;
        this.remaining = remaining;
    }

    //getters and setters
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(Integer exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDrank() {
        return drank;
    }

    public void setDrank(Double drank) {
        this.drank = drank;
    }

    public Double getRemaining() {
        return remaining;
    }

    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }
}