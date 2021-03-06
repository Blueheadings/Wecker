package com.example.wecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;

/**
 * @author Willi Hollatz
 * SMSB4, 17952
 */


public class AlertReceiver extends BroadcastReceiver {

    public static Ringtone r;

    @Override
    public void onReceive(Context context, Intent intent) {
        r = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        r.setStreamType(AudioManager.STREAM_ALARM);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            r.setLooping(true);
        }
        r.play();

        Intent intent1 = new Intent(context, SnoozeActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
