package com.shajeeth.ncd_predictor;


public class UserData {

    private double height, weight, hip, waist, bmi, hwRatio;
    private String month;

    public UserData() {
    }

    public UserData(double bmi, String month) {
        this.bmi = bmi;
        this.month = month;
    }

    public UserData(double bmi, double hwRatio, String month) {
        this.bmi = bmi;
        this.hwRatio = hwRatio;
        this.month = month;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHip() {
        return hip;
    }

    public void setHip(double hip) {
        this.hip = hip;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getHwRatio() {
        return hwRatio;
    }

    public void setHwRatio(double hwRatio) {
        this.hwRatio = hwRatio;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}