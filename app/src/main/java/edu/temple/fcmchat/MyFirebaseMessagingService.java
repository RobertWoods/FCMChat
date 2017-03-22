package edu.temple.fcmchat;

import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * Created by rober_000 on 3/22/2017.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String INTENT_EXTRA = "d00d";
    public static final String INTENT_FILTER = "edu.temple.FCMChat.RECEIVED_MESSAGE";
    public static final String FILENAME = "o3o.messages";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        String message = remoteMessage.getNotification().getBody() + " " + timestamp() + "\n";
        Log.d("Firebase", message);
        FileOutputStream stream;
        try{
            stream = openFileOutput(FILENAME, Context.MODE_APPEND);
            stream.write((message).getBytes());
            stream.close();
        } catch (Exception e){}
        Intent intent = new Intent(INTENT_FILTER);
        intent.putExtra(INTENT_EXTRA, message);
        sendBroadcast(intent);
    }

    private String timestamp() {
        return Calendar.getInstance().getTime().toString();

    }
}
