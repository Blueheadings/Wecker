package com.example.wecker;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;

/**
 * @author Willi Hollatz
 * SMSB4, 17952
 */

public class RingtoneHelper {

    Context context;
    Ringtone r = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));

    RingtoneHelper(Context context){
        this.context = context;
    }

    public void playRingtone(){
        r.play();
    }

    public void stopRingtone(){
        r.stop();
    }

    public Ringtone getR() {
        return r;
    }

    public void setR(Ringtone r) {
        this.r = r;
    }

}
