package com.example.franciscofranco.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;

/**
 * Created by FranciscoFranco on 8/17/16.
 */
public class SimpleIntentService extends IntentService {

    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";

    public SimpleIntentService() {
        super("SimpleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("FRANCO_DEBUG", "service started");
        String msg = intent.getStringExtra(PARAM_IN_MSG);
        SystemClock.sleep(5000);
        String resultTxt = msg + " "
                + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis());

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(MainActivity.ResponseReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(PARAM_OUT_MSG, resultTxt);
        sendBroadcast(broadcastIntent);
    }
}
