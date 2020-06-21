package com.shajeeth.ncd_predictor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Profile extends AppCompatActivity {
    FirebaseUser user;
    EditText current_password;
    EditText new_password1;
    EditText new_password2;
    TextView first_name;
    TextView last_name;
    TextView age;
    TextView profile_email;
    ToggleButton notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        current_password = findViewById(R.id.current_password);
        new_password1 = findViewById(R.id.new_password1);
        new_password2 = findViewById(R.id.new_password2);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        age = findViewById(R.id.age);
        profile_email = findViewById(R.id.profile_email);
        notifications = findViewById(R.id.notifications);
        getProfile();
        notifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent myIntent = new Intent(getApplicationContext(), NotifyService.class);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    PendingIntent pendingIntent = PendingIntent.getService(Profile.this, 0, myIntent, 0);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.HOUR, 0);
                    calendar.set(Calendar.AM_PM, Calendar.AM);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60 * 24 * 30, pendingIntent);
                } else {
                    // The toggle is disabled
                }
            }
        });


    }

    public void change_password(View view) {

        if (new_password1.getText().toString().equals(new_password2.getText().toString())) {
            String newPassword = new_password1.getText().toString();
            user.updatePassword(newPassword)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Profile.this, "Password Changed Successfully.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(Profile.this, "Passwords are not match.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void getProfile() {
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final ArrayList<String> arrayList = new ArrayList();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(currentUser);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    arrayList.add(snapshot.getValue().toString());

                    i++;
                }
                age.setText(arrayList.get(1));
                first_name.setText(arrayList.get(4));
                last_name.setText(arrayList.get(5));
                profile_email.setText(arrayList.get(3));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }


}
