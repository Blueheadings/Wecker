package com.example.wecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannel1Notification("Alarm" , "Your AlarmManager is working.");
        notificationHelper.getManager().notify(1, nb.build());

//        RingtoneHelper ringtoneHelper = new RingtoneHelper(context);
//        ringtoneHelper.playRingtone();

        Ringtone r = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
        r.play();
    }
}
