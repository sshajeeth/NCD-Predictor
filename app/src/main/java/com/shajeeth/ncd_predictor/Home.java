package com.shajeeth.ncd_predictor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Home extends AppCompatActivity {

    Spinner bmi_units;
    Spinner whr_units;
    String bmi_unit;
    String whr_unit;
    SeekBar bmi_weight;
    SeekBar bmi_height;
    SeekBar waist;
    SeekBar hip;
    TextView weight_label;
    TextView height_label;
    TextView waist_label;
    TextView hip_label;
    Float weight_;
    Float height_;
    Float waist_;
    Float hip_;
    double bmi;
    TextView bmi_result;
    TextView whr_result;
    String bmi_result_;
    String whr_result_;
    double whr;
    private static DecimalFormat df = new DecimalFormat("0.00");
    Button possibilities;
    DatabaseReference mDatabase;
    String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        possibilities = findViewById(R.id.possibilities);
        date = new SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(new Date());
//      Spinner
        bmi_units = findViewById(R.id.bmi_units);
        whr_units = findViewById(R.id.whr_units);
        final ArrayAdapter<CharSequence> bmi_adapter = ArrayAdapter.createFromResource(this, R.array.bmi_units, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> whr_adapter = ArrayAdapter.createFromResource(this, R.array.whr_units, android.R.layout.simple_spinner_item);
        bmi_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        whr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bmi_units.setAdapter(bmi_adapter);
        whr_units.setAdapter(whr_adapter);

//      SeekBar
        bmi_weight = findViewById(R.id.bmi_weight);
        bmi_height = findViewById(R.id.bmi_height);
        waist = findViewById(R.id.waist);
        hip = findViewById(R.id.hip);

//      TextView
        weight_label = findViewById(R.id.weight_label);
        height_label = findViewById(R.id.height_label);
        waist_label = findViewById(R.id.waist_label);
        hip_label = findViewById(R.id.hip_label);
        bmi_result = findViewById(R.id.bmi_result);
        whr_result = findViewById(R.id.whr_result);

        bmi_weight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                weight_ = ((float)progress);
                weight_label.setText(String.valueOf(weight_));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bmi_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                height_ = ((float)progress);
                height_label.setText(String.valueOf(height_));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        waist.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                waist_ = ((float)progress);
                waist_label.setText(String.valueOf(waist_));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        hip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hip_ = ((float)progress);
                hip_label.setText(String.valueOf(hip_));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bmi_units.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bmi_unit = parent.getItemAtPosition(position).toString();
//                Toast.makeText(Home.this, bmi_unit, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        whr_units.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                whr_unit = parent.getItemAtPosition(position).toString();
//                Toast.makeText(Home.this, whr_unit, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void predict_ncd(View view) {
        if(String.valueOf(bmi_unit).equals("Kg/m²")){
            bmi = weight_/(height_*height_);

        }else if(String.valueOf(bmi_unit).equals("lbs/in²")){
            bmi = 703*(weight_/(height_*height_));
        }
//
        if(String.valueOf(whr_unit).equals("inches")){
            whr = waist_/hip_;
        }else if (String.valueOf(whr_unit).equals("cm")){
            whr = (waist_/hip_)*2.54;
        }



        bmi_result_ = "Your BMI is " + df.format(bmi) + bmi_unit;
        whr_result_ = "Your WHR is " + df.format(whr) + whr_unit;
        bmi_result.setText(bmi_result_);

        whr_result.setText(whr_result_);
        bmi_result.setText(bmi_result_);



        if(bmi<18.5){
            possibilities.setText("More Info");
            bmi_weight.setEnabled(false);
            bmi_height.setEnabled(false);
            waist.setEnabled(false);
            hip.setEnabled(false);
            String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("height").setValue(height_);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("weight").setValue(weight_);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("bmi").setValue(bmi);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("month").setValue(date);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("hip").setValue(hip_);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("waist").setValue(waist_);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("hwRatio").setValue(whr);
            possibilities.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int SPLASH_TIME_OUT = 1000;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Home.this, NCD_Page.class);
                            intent.putExtra("bmi", bmi);
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            finish();
                        }
                    }, SPLASH_TIME_OUT);
                }
            });

        } else if(bmi>=18.5 && bmi<22.9){
            possibilities.setText("More Info");
            bmi_weight.setEnabled(false);
            bmi_height.setEnabled(false);
            waist.setEnabled(false);
            hip.setEnabled(false);
            String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("height").setValue(height_);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("weight").setValue(weight_);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("bmi").setValue(bmi);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("month").setValue(date);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("hip").setValue(hip_);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("waist").setValue(waist_);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("hwRatio").setValue(whr);
            possibilities.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int SPLASH_TIME_OUT = 1000;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Home.this, NCD_Page.class);
                            intent.putExtra("bmi", bmi);
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            finish();
                        }
                    }, SPLASH_TIME_OUT);
                }
            });

        }else{
            possibilities.setText("More Info");
            bmi_weight.setEnabled(false);
            bmi_height.setEnabled(false);
            waist.setEnabled(false);
            hip.setEnabled(false);
            String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("height").setValue(height_);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("weight").setValue(weight_);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("bmi").setValue(bmi);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("month").setValue(date);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("hip").setValue(hip_);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("waist").setValue(waist_);
            mDatabase.child(currentUser).child("MonthlyData").child(date).child("hwRatio").setValue(whr);
            possibilities.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int SPLASH_TIME_OUT = 1000;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Home.this, NCD_Page.class);
                            intent.putExtra("bmi", bmi);
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            finish();
                        }
                    }, SPLASH_TIME_OUT);

                }
            });

        }






    }

    public void bmi_popup(View view) {

    }

    public void whr_popup(View view) {
    }
}
