package com.shajeeth.ncd_predictor;

import java.text.DateFormat;

public class User {
    public String fname, lname, email, password1, gender, age, created_at;

    public User(String fname, String lname, String email, String password1, String gender, String age, String created_at) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password1 = password1;
        this.gender = gender;
        this.age = age;
        this.created_at = created_at;
    }

}