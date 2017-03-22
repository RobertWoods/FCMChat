package edu.temple.fcmchat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> messages = new ArrayList<>();
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutContent);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //if(intent.getAction()==) TODO: test for specific intent later
                String message = intent.getStringExtra(MyFirebaseMessagingService.INTENT_EXTRA);
                if(message != null) {
                    messages.add(message);
                    addMessageToLayout(message);
                    Log.d("Firebase", "Message received in intent: " + message);
                } else {
                    Log.d("Firebase", "Intent extra was null");
                }
            }
        };
        registerReceiver(receiver, new IntentFilter(MyFirebaseMessagingService.INTENT_FILTER));
    }

    @Override
    public void onResume(){
        super.onResume();
        try {
            File file = new File(getFilesDir(), MyFirebaseMessagingService.FILENAME);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while(line!=null){
                line = reader.readLine();
                addMessageToLayout(line);
            }
            reader.close();
        } catch (Exception e) {}
    }

    public void addMessageToLayout(String message){
        TextView textView = new TextView(getApplicationContext());
        textView.setText(message);
        linearLayout.addView(textView);
    }

}
