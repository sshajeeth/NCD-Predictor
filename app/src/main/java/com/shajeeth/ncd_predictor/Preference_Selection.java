package com.shajeeth.ncd_predictor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Preference_Selection extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String fname;
    String lname;
    String email;
    String password1;
    String gender_string;
    String age_string;

    RadioGroup gender;
    RadioButton gender_;
    EditText age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference__selection);

        gender = findViewById(R.id.gender);

        age = findViewById(R.id.age);

        Bundle bundle = getIntent().getExtras();
        fname = bundle.getString("fname");
        lname = bundle.getString("lname");
        email = bundle.getString("email");
        password1 = bundle.getString("password1");

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    public void finish(View view) {
        int radioId = gender.getCheckedRadioButtonId();

        gender_ = findViewById(radioId);

        gender_string = gender_.getText().toString();
        age_string = age.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password1)
                .addOnCompleteListener(Preference_Selection.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Preference_Selection.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            User user = new User(fname, lname, email, password1, gender_string, age_string);
                            String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            mDatabase.child(currentuser).setValue(user);
                            int SPLASH_TIME_OUT = 2500;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(Preference_Selection.this, Home.class);

                                    startActivity(intent);
                                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                    finish();
                                }
                            }, SPLASH_TIME_OUT);
                        }
                        }

                });







    }

}
