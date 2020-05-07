package com.example.wecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PreferenceActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    int snoozeVariable;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        editText = findViewById(R.id.editText_snoozeTime);
        button = findViewById(R.id.btn_changeSnoozeTime);
        textView = findViewById(R.id.textView_currentSnoozeTime);

        textView.setText("Die Snooze Zeit beträgt zur Zeit " + SnoozeActivity.getMySnoozeVariable() + " Minute(n)");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snoozeVariable = Integer.parseInt(editText.getText().toString().trim());
                    if(snoozeVariable >= 0 && snoozeVariable <= 100) {
                        SnoozeActivity.setMySnoozeVariable(snoozeVariable);
                        /*editText.setText("Snooze Zeit wurde auf " + snoozeVariable + " Minuten geändert");
                        try {
                            wait(5000000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                         */
                        Toast.makeText(PreferenceActivity.this, "Snooze Zeit wurde auf " + snoozeVariable + " Minuten geändert", Toast.LENGTH_SHORT).show();
                        finish();

                    }else{
                        Toast.makeText(PreferenceActivity.this, "Bitte geben Sie eine Zahl zwischen 0 und 100 ein!", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
