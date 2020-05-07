package com.example.wecker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class SnoozeActivity extends AppCompatActivity {
    
    public Button snooze;
    public Button cancel;
    public static int mySnoozeVariable = 1;
    public static Calendar c;

    public static int getMySnoozeVariable() {
        return mySnoozeVariable;
    }

    public static void setMySnoozeVariable(int mySnoozeVariable) {
        SnoozeActivity.mySnoozeVariable = mySnoozeVariable;
    }

    public static Calendar getC() {
        return c;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snooze);
        
        snooze = findViewById(R.id.btn_snooze);
        cancel = findViewById(R.id.bnt_cancelFromSnooze);
        
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
        
        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snoozeAlarm(v);
            }
        });
    }

    private void snoozeAlarm(View v) {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute + mySnoozeVariable);
        c.set(Calendar.SECOND, 0);

        MainActivity.updateTimeText(c);
        AlertReceiver.r.stop();

        Intent intent = new Intent(this, SnoozeReceiver.class);
        sendBroadcast(intent);

        finish();
    }

    private void cancelAlarm() {
        AlertReceiver.r.stop();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        MainActivity.textView.setText("Alarm canceled");
        finish();
    }
}
