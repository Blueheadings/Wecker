package com.example.wecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SnoozeActivity extends AppCompatActivity {
    
    public Button snooze;
    public Button cancel;
    

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
                snoozeAlarm();
            }
        });
    }

    private void snoozeAlarm() {
    }

    private void cancelAlarm() {
    }
}
