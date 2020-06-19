package com.shajeeth.ncd_predictor;

import android.icu.text.DateFormat;

public class UserData {
    private DateFormat dateFormat;
    private double height, weight, hip, waist, bmi, hwRatio;

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
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
}
