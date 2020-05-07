package com.example.wecker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Quelle: https://www.youtube.com/watch?v=yrpimdBRk5Q
 */

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    public static TextView textView;
    private NotificationHelper notificationHelper;
    Button btn_opnPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        notificationHelper = new NotificationHelper(this);
        Button btn_timePicker = findViewById(R.id.btn_timpicker);

        btn_timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


        Button btn_cancelAlarm = findViewById(R.id.btn_cancel);
        btn_cancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });


        btn_opnPrefs = findViewById(R.id.btn_opnPref);
        btn_opnPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PreferenceActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);
    }

    public static void updateTimeText(Calendar c){
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        textView.setText(timeText);
    }

    public void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        // Wecker der in der Vergangengheit liegt (nächster Tag) wird sonst direkt abgespielt
        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE, 1);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
    }

    public void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        try {
            alarmManager.cancel(pendingIntent);
            textView.setText("Alarm canceled");

            AlertReceiver.r.stop();
        }catch (NullPointerException e){
            e.printStackTrace();
            Toast.makeText(this, "No Alarm was set", Toast.LENGTH_SHORT).show();
        }
    }

}
