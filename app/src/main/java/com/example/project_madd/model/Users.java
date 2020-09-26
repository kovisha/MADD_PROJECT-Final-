package com.example.project_madd.model;

public class Users {

    private float height;
    private float weight;
    private int age ;
    private float waist ;
    private float bmi;
    private float bmr ;
    private float wperecn ;

    public Users(float height, float weight, int age, float waist, float bmi, float bmr, float wperecn) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.waist = waist;
        this.bmi = bmi;
        this.bmr = bmr;
        this.wperecn = wperecn;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public float getBmr() {
        return bmr;
    }

    public void setBmr(float bmr) {
        this.bmr = bmr;
    }

    public float getWperecn() {
        return wperecn;
    }

    public void setWperecn(float wperecn) {
        this.wperecn = wperecn;
    }
}
