package com.shajeeth.ncd_predictor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Monthly_Report extends AppCompatActivity {
    BarChart bmi_chart;
    BarChart whr_chart;
    BarDataSet barDataSet;
    BarData barData;
    XAxis xAxis;
    IndexAxisValueFormatter formatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly__report2);

        bmi_chart = findViewById(R.id.bmi_chart);
        whr_chart = findViewById(R.id.whr_chart);
        bmi_chart_();
        whr_chart_();

    }

    private ArrayList getData(ArrayList<Float> values) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for(int i=0; i<values.size();i++){
            entries.add(new BarEntry((float) i, values.get(i)));
        }

//        entries.add(new BarEntry(1f, 80f));
//        entries.add(new BarEntry(2f, 60f));
//        entries.add(new BarEntry(3f, 50f));
//        entries.add(new BarEntry(4f, 70f));
//        entries.add(new BarEntry(5f, 60f));
        return entries;
    }


    public void bmi_chart_() {
        final ArrayList<String> months = new ArrayList<>();
        final ArrayList<Float> bmi = new ArrayList<>();
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(currentUser).child("MonthlyData");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                months.clear();
                int i=0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserData data = snapshot.getValue(UserData.class);
                    months.add(data.getMonth());
                    bmi.add((float) data.getBmi());
                    System.out.println(bmi.get(i));
                    i++;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final Handler handler = new Handler();
        final int delay = 3000; //milliseconds

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(! months.isEmpty() && !bmi.isEmpty()){
                    barDataSet = new BarDataSet(getData(bmi), "BMI");
                    barDataSet.setBarBorderWidth(0.9f);
                    barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                    barData = new BarData(barDataSet);
                    xAxis = bmi_chart.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    formatter = new IndexAxisValueFormatter(months);
                    xAxis.setGranularity(1f);
                    xAxis.setValueFormatter(formatter);

                    bmi_chart.setData(barData);
                    bmi_chart.setFitBars(true);



                    bmi_chart.animateXY(1000, 1000);
                    bmi_chart.invalidate();
                } else{
                    System.out.println("Array list Empty");
                    handler.postDelayed(this, delay);
                }
            }
        },delay);
    }


    public void whr_chart_() {
        final ArrayList<String> months = new ArrayList<>();
        final ArrayList<Float> whr = new ArrayList<>();
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(currentUser).child("MonthlyData");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                months.clear();
                int i=0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserData data = snapshot.getValue(UserData.class);
                    months.add(data.getMonth());
                    whr.add((float) data.getHwRatio());
                    System.out.println(whr.get(i));
                    i++;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final Handler handler = new Handler();
        final int delay = 3000; //milliseconds

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(! months.isEmpty() && !whr.isEmpty()){
                    barDataSet = new BarDataSet(getData(whr), "WHR");
                    barDataSet.setBarBorderWidth(0.9f);
                    barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                    barData = new BarData(barDataSet);
                    xAxis = whr_chart.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    formatter = new IndexAxisValueFormatter(months);
                    xAxis.setGranularity(1f);
                    xAxis.setValueFormatter(formatter);

                    whr_chart.setData(barData);
                    whr_chart.setFitBars(true);



                    whr_chart.animateXY(1000, 1000);
                    whr_chart.invalidate();
                } else{
                    System.out.println("Array list Empty");
                    handler.postDelayed(this, delay);
                }
            }
        },delay);
    }

}

