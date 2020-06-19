package com.shajeeth.ncd_predictor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CheckBMI extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    Spinner units;
    EditText weight;
    EditText height;
    TextView bmi;
    Button predict_ncd;
    String opt;
    double bmi_;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_bmi);
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        units = findViewById(R.id.unit);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        bmi = findViewById(R.id.bmi);
        predict_ncd = findViewById(R.id.predict_ncd);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        units.setAdapter(adapter);
        units.setOnItemSelectedListener(this);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        opt = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public double find_bmi(double height, double weight){
        double bmi=0;

        if (opt.equals("Kg/m²")) {
            bmi = weight / (height * height);
        }else if (opt.equals("lbs/in²")){
            bmi = 703 * weight / (height * height);
        }
        return Math.round(bmi*100.0)/100.0;
    }

    public void calculate_bmi(View view) {
        bmi_ = find_bmi(Double.parseDouble(height.getText().toString()), Double.parseDouble(weight.getText().toString()));
//        System.out.println(date);
        mDatabase.child("ss").child(date).child("height").setValue(Double.parseDouble(height.getText().toString()));
        mDatabase.child("ss").child(date).child("weight").setValue(Double.parseDouble(weight.getText().toString()));
        mDatabase.child("ss").child(date).child("bmi").setValue(bmi_);
        if (opt.equals("Kg/m²")) {
            bmi.setText("Your BMI is " + bmi_ + "Kg/m²");
        }else if (opt.equals("lbs/in²")){
            bmi.setText("Your BMI is " + bmi_ + "lbs/in²");
        }

        if (bmi_>=18.5 && bmi_<=22.9) {
            predict_ncd.setVisibility(View.INVISIBLE);
        }else{
            predict_ncd.setVisibility(View.VISIBLE);
        }
    }

    public void predict_ncd(View view) {
        int SPLASH_TIME_OUT = 2500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CheckBMI.this, NCD.class);
                intent.putExtra("bmi", bmi_);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
