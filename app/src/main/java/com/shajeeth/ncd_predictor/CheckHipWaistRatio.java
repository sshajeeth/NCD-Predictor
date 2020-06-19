package com.shajeeth.ncd_predictor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CheckHipWaistRatio extends AppCompatActivity {
    private DatabaseReference mDatabase;
    EditText hip;
    EditText waist;
    TextView ratio;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_hip_waist_ratio);
        hip = findViewById(R.id.hip);
        waist = findViewById(R.id.waist);
        ratio = findViewById(R.id.ratio);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        date = new SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(new Date());

    }

    public void find_hip_waist_ratio(View view) {
        double ratio_amount = Double.parseDouble(waist.getText().toString())/Double.parseDouble(hip.getText().toString());
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase.child(currentUser).child(date).child("hip").setValue(Double.parseDouble(hip.getText().toString()));
        mDatabase.child(currentUser).child(date).child("waist").setValue(Double.parseDouble(waist.getText().toString()));
        mDatabase.child(currentUser).child(date).child("hwRatio").setValue(ratio_amount);
        if (ratio_amount<=0.95){
            ratio.setText("Low Health Risk" + ratio_amount);
        }else if (ratio_amount>=0.96 && ratio_amount<1.0){
            ratio.setText("Moderate Risk" + ratio_amount);
        }else{
            ratio.setText("High Risk" + ratio_amount);
        }
    }
}
