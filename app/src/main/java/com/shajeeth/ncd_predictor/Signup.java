package com.shajeeth.ncd_predictor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    EditText fname;
    EditText lname;
    EditText email;
    EditText password1;
    EditText password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
    }




    public void signup(View view) {
        if (fname.getText().toString().equals("") || lname.getText().toString().equals("") || email.getText().toString().equals("") || password1.getText().toString().equals("") || password2.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill all fields!",
                    Toast.LENGTH_LONG).show();
        }else if (!password1.getText().toString().equals(password2.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Passwords are not match!",
                    Toast.LENGTH_LONG).show();
        }else if (Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            int SPLASH_TIME_OUT = 1000;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Signup.this, Preference_Selection.class);
                    intent.putExtra("fname",fname.getText().toString());
                    intent.putExtra("lname",lname.getText().toString());
                    intent.putExtra("email",email.getText().toString());
                    intent.putExtra("password1",password1.getText().toString());
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }else {
            Toast.makeText(getApplicationContext(), "Email is not valid!",
                    Toast.LENGTH_LONG).show();
        }
    }
}
