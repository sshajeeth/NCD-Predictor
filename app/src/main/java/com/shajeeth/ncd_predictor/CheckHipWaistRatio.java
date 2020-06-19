package com.shajeeth.ncd_predictor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CheckHipWaistRatio extends AppCompatActivity {
    EditText hip;
    EditText waist;
    TextView ratio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_hip_waist_ratio);
        hip = findViewById(R.id.hip);
        waist = findViewById(R.id.waist);
        ratio = findViewById(R.id.ratio);
    }

    public void find_hip_waist_ratio(View view) {
        double ratio_amount = Double.parseDouble(waist.getText().toString())/Double.parseDouble(hip.getText().toString());
        if (ratio_amount<=0.95){
            ratio.setText("Low Health Risk" + ratio_amount);
        }else if (ratio_amount>=0.96 && ratio_amount<1.0){
            ratio.setText("Moderate Risk" + ratio_amount);
        }else{
            ratio.setText("High Risk" + ratio_amount);
        }
    }
}
