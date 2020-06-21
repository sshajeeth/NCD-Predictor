package com.shajeeth.ncd_predictor;

import java.text.DateFormat;

public class User {
    private String fname, lname, email, password1, gender, age, created_at;

    public User(String fname, String lname, String email, String password1, String gender, String age, String created_at) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password1 = password1;
        this.gender = gender;
        this.age = age;
        this.created_at = created_at;
    }

    public User(){

    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }





}