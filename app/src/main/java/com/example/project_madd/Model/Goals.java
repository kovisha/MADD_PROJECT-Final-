package com.example.project_madd.Model;

public class Goals {

     private Integer userID;
     private String goal_one;
     private String goal_two;
     private String goal_three;
     private String goal_four;

    public Goals(Integer userID, String goal_one, String goal_two, String goal_three, String goal_four) {
        this.userID = userID;
        this.goal_one = goal_one;
        this.goal_two = goal_two;
        this.goal_three = goal_three;
        this.goal_four = goal_four;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getGoal_one() {
        return goal_one;
    }

    public void setGoal_one(String goal_one) {
        this.goal_one = goal_one;
    }

    public String getGoal_two() {
        return goal_two;
    }

    public void setGoal_two(String goal_two) {
        this.goal_two = goal_two;
    }

    public String getGoal_three() {
        return goal_three;
    }

    public void setGoal_three(String goal_three) {
        this.goal_three = goal_three;
    }

    public String getGoal_four() {
        return goal_four;
    }

    public void setGoal_four(String goal_four) {
        this.goal_four = goal_four;
    }
}
