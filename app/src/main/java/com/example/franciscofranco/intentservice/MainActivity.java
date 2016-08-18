package com.example.franciscofranco.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private ResponseReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver,filter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void onClick(View view) {
        Log.d("FRANCO_DEBUG", "button clicked");
        String strInputMsg = editText.getText().toString();
        Intent msgIntent = new Intent(this, SimpleIntentService.class);
        msgIntent.putExtra(SimpleIntentService.PARAM_IN_MSG, strInputMsg);
        startService(msgIntent);
    }

    public class ResponseReceiver extends BroadcastReceiver{
        public static final String ACTION_RESP =
                "com.example.franciscofranco.intentservice.action.MESSAGE_PROCESSED";

        @Override
        public void onReceive(Context context, Intent intent) {
            TextView result = (TextView) findViewById(R.id.textResult);
            String text = intent.getStringExtra(SimpleIntentService.PARAM_OUT_MSG);
            result.setText(text);
        }
    }
}
