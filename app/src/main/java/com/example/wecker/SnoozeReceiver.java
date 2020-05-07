package com.example.wecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.text.DateFormat;
import java.util.Calendar;

import androidx.core.app.NotificationCompat;

/**
 * @author Willi Hollatz
 * SMSB4, 17952
 */

public class SnoozeReceiver extends BroadcastReceiver {

    String nextAlarm;
    Calendar c;


    @Override
    public void onReceive(Context context, Intent intent) {
        c = SnoozeActivity.getC();
        nextAlarm = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannel1Notification("Wecker" , "Der nächste Wecker klingelt um " + nextAlarm + " Uhr.");
        notificationHelper.getManager().notify(1, nb.build());
    }
}
