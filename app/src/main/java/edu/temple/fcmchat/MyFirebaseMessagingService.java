package edu.temple.fcmchat;

import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by rober_000 on 3/22/2017.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String INTENT_EXTRA = "d00d";
    public static final String INTENT_FILTER = "edu.temple.FCMChat.RECEIVED_MESSAGE";
    public static final String FILENAME = "o3o.messages";
    private static final String LOG = "FMS";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        String message = remoteMessage.getNotification().getBody() +
                " " + Calendar.getInstance().getTime().toString();
        Log.d(LOG, message);
        FileOutputStream stream;
        try{
            stream = openFileOutput(FILENAME, Context.MODE_APPEND);
            stream.write((message+"\n").getBytes());
            stream.close();
        } catch (IOException e){
            Log.d(LOG, "Failed to write to file");
        }
        Intent intent = new Intent(INTENT_FILTER);
        intent.putExtra(INTENT_EXTRA, message);
        sendBroadcast(intent);
    }

}
