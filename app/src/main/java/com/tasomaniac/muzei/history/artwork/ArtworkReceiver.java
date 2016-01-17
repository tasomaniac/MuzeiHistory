package com.tasomaniac.muzei.history.artwork;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class ArtworkReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, ArtworkService.class);
        service.putExtras(intent);
        startWakefulService(context, service);
    }
}
