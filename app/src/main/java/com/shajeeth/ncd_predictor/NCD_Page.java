package com.shajeeth.ncd_predictor;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class NCD_Page extends AppCompatActivity {
    double bmi;
    TextView bmi_sentence;
    TextView bp_status;
    TextView cholesterol_status;
    TextView diabetes_status;
    TextView cancer_status;
    TextView fat_liver_status;
    TextView arthritis_status;
    TextView heart_disease_status;
    TextView diet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncd__page);
        bmi_sentence = findViewById(R.id.bmi_sentence);
        bp_status = findViewById(R.id.bp_status);
        cholesterol_status = findViewById(R.id.cholesterol_status);
        diabetes_status = findViewById(R.id.diabetes_status);
        cancer_status = findViewById(R.id.cancer_status);
        fat_liver_status = findViewById(R.id.fat_liver_status);
        arthritis_status = findViewById(R.id.arthritis_status);
        heart_disease_status = findViewById(R.id.heart_disease_status);
        diet = findViewById(R.id.diet);
        Bundle bundle = getIntent().getExtras();
        bmi = bundle.getDouble("bmi");

        diet_suggestions(bmi);



    }

    public void monthly_report(View view) {
        int SPLASH_TIME_OUT = 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(NCD_Page.this, Monthly_Report.class);
                intent.putExtra("bmi", bmi);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    public void echannel(View view) {
        String url = "https://www.echannelling.com/Echannelling/index";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void diet_suggestions(double bmi) {
        if (bmi < 18.5) {
            bmi_sentence.setText("Your BMI is on Underweight Category!");
            bp_status.setTextColor(Color.YELLOW);
            bp_status.setText("Low BP");
            diabetes_status.setTextColor(Color.RED);
            diabetes_status.setText("Risk");
            cancer_status.setTextColor(Color.RED);
            cancer_status.setText("Risk");
            arthritis_status.setTextColor(Color.RED);
            arthritis_status.setText("Risk");
            diet.setText(R.string.underweight);
        } else if (bmi >= 18.5 && bmi <= 22.9) {
            bmi_sentence.setText("Your BMI is in Optimum Level!");
            bp_status.setTextColor(Color.GREEN);
            bp_status.setText("None");
            cholesterol_status.setTextColor(Color.GREEN);
            cholesterol_status.setText("None");
            diabetes_status.setTextColor(Color.GREEN);
            diabetes_status.setText("None");
            cancer_status.setTextColor(Color.GREEN);
            cancer_status.setText("None");
            fat_liver_status.setTextColor(Color.GREEN);
            fat_liver_status.setText("None");
            arthritis_status.setTextColor(Color.GREEN);
            arthritis_status.setText("None");
            heart_disease_status.setTextColor(Color.GREEN);
            heart_disease_status.setText("None");
            diet.setText("Keep your current diet plan!");
        } else {
            bmi_sentence.setText("Your BMI is on Overweight Category!");
            bp_status.setTextColor(Color.RED);
            bp_status.setText("Risk");
            cholesterol_status.setTextColor(Color.RED);
            cholesterol_status.setText("Risk");
            diabetes_status.setTextColor(Color.RED);
            diabetes_status.setText("Risk");
            cancer_status.setTextColor(Color.RED);
            cancer_status.setText("Risk");
            fat_liver_status.setTextColor(Color.RED);
            fat_liver_status.setText("Risk");
            arthritis_status.setTextColor(Color.RED);
            arthritis_status.setText("Risk");
            heart_disease_status.setTextColor(Color.RED);
            heart_disease_status.setText("Risk");
            diet.setText(R.string.overweight);
        }

    }


}
